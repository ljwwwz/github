package com.coolshow.jeesite.modules.cmag.scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.coolshow.frm.jeesite.common.config.Global;
import com.coolshow.frm.jeesite.modules.sys.entity.User;
import com.coolshow.frm.jeesite.modules.sys.service.SystemService;
import com.coolshow.jeesite.common.tool.DateTool;
import com.coolshow.jeesite.common.tool.SendMail;
import com.coolshow.jeesite.common.tool.entity.MailEntity;
import com.coolshow.jeesite.modules.cmag.conWarn.entity.ContractWarn;
import com.coolshow.jeesite.modules.cmag.conWarn.service.ContractWranService;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;
import com.coolshow.jeesite.modules.cmag.newcontract.service.ContractDataService;

/**
 * 合同提醒定时任务
 * @author LJW
 */
@Service
@Lazy(false)
public class ContractWarnTask {
	
	private static final SimpleDateFormat dtaeFormat = new SimpleDateFormat("HH:mm:ss");
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(ContractWarnTask.class);
	
	@Autowired
	private ContractWranService contractWranService;
	@Autowired
	private ContractDataService contractDataService;
	@Autowired
	private SystemService systemService;
	
	/**
	  * @Description:定时查询合同提醒列表，改变合同提醒状态
	  * @param   
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年7月10日
	  * 间隔5秒执行0/5 * * * * ?    0 0/10 * * * ?(0 0/20 * * * ?) 每10分钟       0 0 0/1 * * ? 每一小时    0-59 * * * * * 每秒执行一次
	 */
    @Scheduled(cron="0 0 0/1 * * ?") 
    public void upateConWranState(){
    	logger.warn("【欢迎您使用酷秀合同管理系统-邮件提醒功能--The time is now:"+dtaeFormat.format(new Date())+"】");
    	try {
    		List<ContractWarn> cWarns = contractWranService.findAllListNoWhere();
	    	for (ContractWarn contractWarn:cWarns) {
	    		//判断是否已经发送过邮件，如果发送不再发送
	    		String mail_state = contractWarn.getMail_state();
	    		if ("02".equals(mail_state)) {
					continue;
				}
				Contract contract = new Contract();
	    		String contract_no = contractWarn.getWarn_contractNo();
				contract.setContract_no(contract_no);
				contract = contractDataService.findContract(contract_no);
				int day_num = 0;
				if (contract != null) {
					/*String remind_state = contract.getRemind_state();
					if ("02".equals(remind_state)) {
						continue;
					}*/
					day_num = DateTool.daysBetween(DateTool.uf_ShortStringToDateTime(contract.getEnd_date()), DateTool.uf_ShortStringToDateTime(contractWarn.getRemind_time()));
					if (day_num == 0) {
						MailEntity mailEntity = new MailEntity();
						User warn_user = systemService.getUser(contractWarn.getRemind_person());
						mailEntity.setTo_user(warn_user.getEmail());
						String subject = "合同管理系统邮件提醒(编号："+contract.getContract_no_name()+")";
						mailEntity.setSubject(subject);
						mailEntity.setContent(contractWarn.getRemind_content());
						mailEntity.setFrom_user(Global.getConfig("mail.from.user"));
						mailEntity.setFrom_userNmae(Global.getConfig("mail.from.user.name"));
						mailEntity.setFrom_userPsw(Global.getConfig("mail.from.user.password"));
						mailEntity.setContract_name(contract.getContract_name());
						mailEntity.setContract_no(contract.getContract_no_name());
						mailEntity.setRemind_time(contractWarn.getRemind_time());
						//if (sendMail(mailEntity)) {
						if (SendMail.sendFtlMail(mailEntity)) {
							//更新合同提醒状态为 已提醒
							contract.setRemind_state("02");
							contractDataService.updateContractWarnSate(contract);
							//更新提醒中 邮件状态 为已发送
							contractWarn.setMail_state("02");
							contractWranService.updateState(contractWarn);
						}else {
							logger.warn("【欢迎您使用酷秀合同管理系统-邮件提醒功能--The time is now:"+dtaeFormat.format(new Date())+"--发送邮件失败！】");
						}
					}
				}
			}
		} catch (ParseException e) {
			logger.error("【合同管理系统--邮件提醒功能异常】"+e.toString());
		} catch (Exception e) {
			logger.error("【合同管理系统--邮件提醒功能异常】"+e.toString());
		}
    }
}
