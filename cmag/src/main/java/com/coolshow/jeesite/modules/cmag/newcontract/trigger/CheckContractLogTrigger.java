package com.coolshow.jeesite.modules.cmag.newcontract.trigger;

import java.util.Arrays;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.coolshow.jeesite.common.tool.StringTool;
import com.coolshow.jeesite.modules.cmag.conmanger.service.ConManagerDataService;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.CheckContractLog;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;
import com.coolshow.jeesite.modules.cmag.newcontract.service.CheckContractLogService;

/**
 * 记录查看合同的触发器
 * @author brj
 *
 */
@Aspect
public class CheckContractLogTrigger {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckContractLogTrigger.class);
	
	@Resource
	private CheckContractLogService checkContractLogService;
	@Autowired
	private ConManagerDataService conManagerDataService;
	
	@Pointcut("(execution(* com.coolshow.jeesite.modules.cmag.conmanger.web.ConMangerDataController.checkContract(..))  "
			+ "|| execution(* com.coolshow.jeesite.modules.cmag.conmanger.web.ConMangerDataController.getEnclosure(..)) "
			+ "|| execution(* com.coolshow.jeesite.modules.cmag.conmanger.web.ConMangerDataController.downloadFile(..)))")
	public void checkConstractLogPointcut(){}
	
	/**
	 * 此方法记录查看合同动作的信息
	 * @param pjp
	 */
	@Around("checkConstractLogPointcut()")
	public Object record(ProceedingJoinPoint pjp){
		Object res = null;
		//新建一个日志类
		CheckContractLog checkContractLog = new CheckContractLog();
		checkContractLog.setUuid(StringTool.getUuidRemoveSeparator());
		String result = "";
		try {
			//获取请求操作
			String url = (String) pjp.getSignature().getName();
			//获取当前操作者登录名
			String loginName = checkContractLog.getCurrentUser().getLoginName();
			//获取当前操作者姓名
			String name = checkContractLog.getCurrentUser().getName();
			//获取当前所操作的合同
			Contract contract =  (Contract) pjp.getArgs()[0];
			 //当提交的参数是contract_no以及contract_type_dm时，使用这个获取完整合同信息
			if(StringTool.isEmpty(contract.getContract_no(),contract.getContract_type_dm())){ 
				contract = conManagerDataService.findContractInfo(contract);
			}else if(StringTool.isEmpty(contract.getEnclosure_id())){
				contract = conManagerDataService.getContractByEnclosureId(contract.getEnclosure_id());
			}
			//获取合同名
			String contract_name = contract.getContract_name();
			String contract_no = contract.getContract_no();
			
			checkContractLog.setUrl(url);
			checkContractLog.setLoginName(loginName);
			checkContractLog.setName(name);
			checkContractLog.setArgs(Arrays.asList(pjp.getArgs()).toString());
			checkContractLog.setContract_name(contract_name);
			checkContractLog.setContract_no(contract_no);
			
			res = pjp.proceed();
			result = "success";
			checkContractLog.setResult(result);
			logger.info("record "+url);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			result = "failed";
			checkContractLog.setResult(result);
			e.printStackTrace();
		}finally {
			checkContractLogService.save(checkContractLog);
		}
		return res;
	}
	
	public CheckContractLogTrigger(){}
}
