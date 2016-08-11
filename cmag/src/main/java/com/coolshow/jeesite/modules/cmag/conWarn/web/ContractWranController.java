package com.coolshow.jeesite.modules.cmag.conWarn.web;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.coolshow.frm.jeesite.common.config.Global;
import com.coolshow.frm.jeesite.common.utils.StringUtils;
import com.coolshow.frm.jeesite.common.web.BaseController;
import com.coolshow.frm.jeesite.modules.sys.entity.Menu;
import com.coolshow.frm.jeesite.modules.sys.utils.UserUtils;
import com.coolshow.jeesite.common.datagrid.DataRequest;
import com.coolshow.jeesite.common.datagrid.DataResponse;
import com.coolshow.jeesite.common.tool.ContractTypeEnum;
import com.coolshow.jeesite.common.tool.DateTool;
import com.coolshow.jeesite.common.tool.FormBean;
import com.coolshow.jeesite.common.tool.StringTool;
import com.coolshow.jeesite.common.tool.UserTool;
import com.coolshow.jeesite.common.tool.FormBean.Result;
import com.coolshow.jeesite.modules.cmag.conWarn.entity.ContractWarn;
import com.coolshow.jeesite.modules.cmag.conWarn.service.ContractWranService;
import com.coolshow.jeesite.modules.cmag.conmanger.service.ConManagerDataService;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ConFollow;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.DistributorBase;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.SupplierBase;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ToolEntity;
import com.coolshow.jeesite.modules.cmag.newcontract.service.ContractDataService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.lingala.zip4j.model.EndCentralDirRecord;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 合同提醒
 * ClassName: ContractWranController
 * @author LJW
 * @date 2016年7月05日
 */
@Controller
@RequestMapping(value = "${adminPath}/cmag/conwarn/mag")
public class ContractWranController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ContractWranController.class);
	
	@Autowired
	private ConManagerDataService conManagerDataService;
	@Autowired
	private ContractDataService contractDataService;
	@Autowired 
	private ContractWranService contractWranService;
	
	/**
	  * @Description: 显示合同提醒新建页面
	  * @param  @param contract
	  * @param  @param model
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年7月13日
	 */
	@RequestMapping(value = "showNewWarnPage")
	public String showNewWarnPage(Contract contract, Model model){
		return "modules/cmag/conWarn/newConWarnPage";
	}
	
	/**
	  * @Description: 显示查询列表页面
	  * @param  @param contract
	  * @param  @param model
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年7月5日
	 */
	@RequestMapping(value="showMainPage")
	public String showMainPage(Contract contract, Model model){
		return "modules/cmag/conWarn/contractWarnMainPage";
	}
	
	/**
	  * @Description: 显示合同维护列表页面
	  * @param  @param contract
	  * @param  @param request
	  * @param  @param response
	  * @param  @param model
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @date 2016年5月10日
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Contract contract, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/cmag/conWarn/conWarnQueryPage";
	}

	
	/**
	  * @Description: 查询
	  * @param  @param contract
	  * @param  @param request
	  * @param  @param response
	  * @param  @param model
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @date 2016年5月10日
	 */
	@ResponseBody  
	@RequestMapping(value="list1")
	@SuppressWarnings({ "rawtypes", "unused" })
    public DataResponse list1(@RequestParam(defaultValue="1",value="page") String page, @RequestParam(defaultValue="10",value="rows") String rows, 
    							Model model,HttpServletRequest httpServletRequest){  
		try {
			Contract contract = new Contract();
			ContractWarn contractWarn = new ContractWarn();
			DataRequest request = new DataRequest();
			String curUserRoleType = UserUtils.getRoleList().get(0).getRoleType();
			String curUserId = UserUtils.getUser().getId();
            request.setPage(StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page));  
            request.setRows(StringUtils.isEmpty(rows) ? 10 : Integer.valueOf(rows));
            contract = getRequestPara(httpServletRequest);
            List<Menu> userMenus = UserTool.getUserOperBtn("合同提醒维护管理");
            /**
             * 以下判断是否是管理员登录
             */
            List<Contract> tmplistAll = Lists.newArrayList();
            List<ToolEntity> toolEntities = Lists.newArrayList();
            if("1".equals(curUserId) || "assignment".equals(curUserRoleType) || "security-role".equals(curUserRoleType)){
				tmplistAll = contractDataService.findConWarnList(contract);
				tmplistAll = addBtnQx(tmplistAll,userMenus);
				logger.warn("当前用户合同提醒维护功能中拥有的操作权限为：【"+getSplitStr(userMenus)+"】");
				return contractWranService.search(request,getShowList(tmplistAll,"N"));
			}
            /**
             * 以下是创建人和被提醒人 判断
             */
            contractWarn.setRemind_person(curUserId);
            long num = contractWranService.findCount(contractWarn);
            if (num >= 1) {
				contract.setConWarn(contractWarn);
				tmplistAll = contractDataService.findConWarnList(contract);
				tmplistAll = addBtnQx(tmplistAll,userMenus);
				logger.warn("当前用户合同提醒维护功能中拥有的操作权限为：【"+getSplitStr(userMenus)+"】");
				return contractWranService.search(request,getShowList(tmplistAll,"Y"));
			}
            contract.setCreate_user(curUserId);
            tmplistAll = contractDataService.findConWarnList(contract);
            tmplistAll = addBtnQx(tmplistAll,userMenus);
            logger.warn("当前用户合同提醒维护功能中拥有的操作权限为：【"+getSplitStr(userMenus)+"】");
			return contractWranService.search(request,getShowList(tmplistAll,"N"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	/**
	  * @Description: 修改和查看合同提醒信息
	  * @param  @param contract
	  * @param  @param model
	  * @param  @param request
	  * @param  @param redirectAttributes
	  * @param  @return
	  * @param  @throws UnsupportedEncodingException 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年7月12日
	 */
	@RequestMapping(value = "form")
	public String form(Contract contract, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		try {
			String contract_no = request.getParameter("contract_no");
			String warn_id = request.getParameter("id");
			String tabType = request.getParameter("tabType");
			contract = contractDataService.findContract(contract_no);
			ContractWarn contractWarn = new ContractWarn();
			contractWarn.setWarn_id(warn_id);
			contractWarn = contractWranService.get(contractWarn);
			contract.setConWarn(contractWarn);
			model.addAttribute("contract", contract);
			model.addAttribute("tabType", tabType);
			return "modules/cmag/conWarn/contractWarnForm";
		} catch (Exception e) {
			addMessage(redirectAttributes, "查询数据异常！"+e.getMessage());
			return "redirect:"+Global.getAdminPath()+"/cmag/conwarn/mag";
		}
	}
	
	/**
	  * @Description: 修改
	  * @param  @param contract
	  * @param  @param request
	  * @param  @param response
	  * @param  @param model
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年7月12日
	 */
	@RequiresPermissions("cmag:conwarn:mag:edit")
	@RequestMapping(value = "update")
	public String update(Contract contract, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		try {
			ContractWarn contractWarn = contract.getConWarn();
			int update_num = contractWranService.upadateConWarn(contractWarn);
			if (update_num >= 1) {
				model.addAttribute("tabType", "1");
				model.addAttribute("contract", contract);
				model.addAttribute("message", "修改【"+contract.getContract_no_name()+"】合同提醒信息成功！");
				return "modules/cmag/conWarn/contractWarnForm";
			}
			model.addAttribute("tabType", "1");
			model.addAttribute("contract", contract);
			model.addAttribute("message", "修改【"+contract.getContract_no_name()+"】合同提醒信息失败！");
			return "modules/cmag/conWarn/contractWarnForm";
		} catch (Exception e) {
			model.addAttribute("tabType", "1");
			model.addAttribute("contract", contract);
			model.addAttribute("message", "修改【"+contract.getContract_no_name()+"】合同提醒信息失败！");
			return "modules/cmag/conWarn/contractWarnForm";
		}
	}
	
	/**
	  * @Description: 删除合同提醒信息
	  * @param  @param id      
	  * @param  @param request
	  * @param  @param response
	  * @param  @param model
	  * @param  @return 
	  * @return FormBean  
	  * @throws
	  * @author LJW
	  * @date 2016年7月12日
	 */
	@RequiresPermissions("cmag:conwarn:mag:del")
	@ResponseBody
	@RequestMapping(value="delWarn")
	public FormBean delWarn(@RequestParam("id")String id,HttpServletRequest request, HttpServletResponse response, Model model){
		FormBean formBean = new FormBean();
		try {
			ContractWarn contractWarn = new ContractWarn();
			contractWarn.setWarn_id(id);
			int delCount = contractWranService.delWarn(contractWarn);
			if (delCount >= 1) {
				formBean.setStatus(Result.SUCCESS);
				formBean.setMsg("删除合同提醒信息成功！");
				return formBean;
			}
			formBean.setStatus(Result.FAILURE);
			formBean.setMsg("删除合同提醒信息失败！");
			return formBean;
		} catch (Exception e) {
			formBean.setStatus(Result.FAILURE);
			formBean.setMsg("删除合同提醒信息失败：原因是【"+e.getMessage()+"】");
		}
		return null;
	}
	
	/**
	  * @Description: 获取请求参数
	  * @param  @param httpServletRequest
	  * @param  @return 
	  * @return Contract  
	  * @throws
	  * @author LJW
	  * @date 2016年6月23日
	 */
	private Contract getRequestPara(HttpServletRequest httpServletRequest) {
		Contract contract = new Contract();
		ContractWarn contractWarn = new ContractWarn();
		String firstParty = httpServletRequest.getParameter("firstParty");
		String secondParty = httpServletRequest.getParameter("secondParty");
		if(firstParty != null && !"".equals(firstParty)){
			String[] firstPartyId = StringTool.getSplitStr(firstParty, "|");
			firstParty = firstPartyId[0];
		}
		if(secondParty != null && !"".equals(secondParty)){
			String[] secondPartyId = StringTool.getSplitStr(secondParty, "|");
			secondParty = secondPartyId[0];
		}
		String contractName = httpServletRequest.getParameter("contractName");
		String contractNo = httpServletRequest.getParameter("contractNo");
		String qdDate = httpServletRequest.getParameter("qdDate");
		String remind_person = httpServletRequest.getParameter("remind_person");
		String remind_state = httpServletRequest.getParameter("remind_state");
		contract.setFirstParty(firstParty);
		contract.setSecondParty(secondParty);
		contract.setContract_no(contractNo);
		contract.setContract_name(contractName);
		contract.setQd_date(qdDate);
		contract.setRemind_state(remind_state);
		contractWarn.setRemind_person(remind_person);
		contract.setConWarn(contractWarn);
		return contract;
	}
	
	/**
	  * @Description: 把查询出来的按钮权限，添加到合同提醒列表中
	  * @param  @param tmpList
	  * @param  @param userMenus
	  * @param  @return 
	  * @return List<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年7月11日
	 */
	private List<Contract> addBtnQx(List<Contract> tmpList,List<Menu> userMenus){
		List<Contract> tmplistAll = Lists.newArrayList();
		for (Contract contractALLs : tmpList) {
			contractALLs.setMenus(getSplitStr(userMenus));
			tmplistAll.add(contractALLs);
		}
		return tmplistAll;
	}
	
	/**
	  * @Description: 把从数据库中查询出来的权限按钮，组装成以逗号分隔的字符串
	  * @param  @param menus
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年7月11日
	 */
	private String getSplitStr(List<Menu> menus){
		StringBuffer str = new StringBuffer();
		for(Menu menu:menus){
			str.append(menu.getName()).append(",");
		}
		str.deleteCharAt(str.length()-1);
		return str.toString();
	}
	
	/**
	  * @Description: 查询合同列表 JSON数据。
	  * @param  @return 
	  * @return List<Map<String,Object>>  
	  * @throws
	  * @author WQH
	  * @date 2016年7月14日
	 */
	@ResponseBody
	@RequestMapping(value = "contractTreeData")
	public List<Map<String, Object>>contractTreeData(){
		List<Map<String, Object>> contractList = Lists.newArrayList();
		getContracts(contractList);
		return contractList;
	}
	
	/**
	  * @Description: 获取合同列表
	  * @param  @param contractList 
	 * @return 
	  * @return void  
	  * @throws
	  * @author WQH
	  * @date 2016年7月14日
	 */
	private List<Map<String, Object>> getContracts(List<Map<String, Object>> contractList) {
		Map<String, Object> tmpContract = Maps.newHashMap();
		tmpContract.put("id", "1");
		tmpContract.put("pId", "");
		tmpContract.put("pIds", "0,");
		tmpContract.put("name", "合同列表");
		contractList.add(tmpContract);
		List<Contract> contracts = contractDataService.findContracts();
		for (int i = 0; i < contracts.size(); i++) {
			Contract contract = contracts.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", contract.getContract_no_name()+"|"+contract.getContract_no());
			map.put("pId", "1");
			map.put("pIds", "1");
			map.put("name", contract.getContract_name());
			contractList.add(map);
		}
		return contractList;
	}
	
	/**
	 * 保存合同提醒信息
	 * @return
	 * @author WQH
	 * @date 2016年7月14日
	 */
	@RequestMapping(value="saveConWarn")
	public String saveConWarn (Contract contract, Model model, HttpServletRequest request){
		ContractWarn conWarn = new ContractWarn();
		String contract_no = request.getParameter("contract_no");
		String contract_noName = request.getParameter("contract_noName");
		String saveFlag = request.getParameter("saveFlag");
		conWarn.setName(contract.getConWarn().getName());
		conWarn.setWarn_contractNo(contract_no);
		contract.setContract_no(contract_no);
		conWarn.setRemind_time(contract.getConWarn().getRemind_time());
		conWarn.setRemind_content(contract.getConWarn().getRemind_content());
		List<ContractWarn> contractWarns = Lists.newArrayList();
		contractWarns = getConWarnInfo(conWarn,contract.getConWarn().getRemind_person(),contract.getConWarn().getRemind_name());
		contractWranService.insertList(contractWarns);
		saveFlag = "Y";
		model.addAttribute("saveFlag",saveFlag);
		model.addAttribute("contract", contract);
		model.addAttribute("conWarn",conWarn);
		model.addAttribute("contract_noName", contract_noName);
		model.addAttribute("message","合同提醒信息保存成功");
		return "modules/cmag/conWarn/newConWarnPage";
	}

	/**
	  * @Description: 返回供前台显示的list
	  * @param  @param tmplistAll
	  * @param  @return
	  * @param  @throws Exception 
	  * @return List<ToolEntity>  
	  * @throws
	  * @author LJW
	  * @date 2016年7月15日
	 */
	private List<ToolEntity> getShowList(List<Contract> tmplistAll,String personFlag) throws Exception{
		List<ToolEntity> toolEntities = Lists.newArrayList();
		try {
			for(Contract contract2:tmplistAll){
				for(ContractWarn contractWarn2:contract2.getContractWarns()){
					ToolEntity toolEntity = new ToolEntity();
					toolEntity.setWarn_id(contractWarn2.getWarn_id());
					toolEntity.setMenus(contract2.getMenus());
					toolEntity.setContract_no(contract2.getContract_no());
					toolEntity.setContract_type_dm(contract2.getContract_type_dm());
					toolEntity.setEnclosure_id(contract2.getEnclosure_id());
					toolEntity.setContract_name(contract2.getContract_name());
					toolEntity.setContract_no_name(contract2.getContract_no_name());
					toolEntity.setFirstPartyName(contract2.getFirstPartyName());
					toolEntity.setSecondPartyName(contract2.getSecondPartyName());
					toolEntity.setQd_date(contract2.getQd_date());
					toolEntity.setRemind_state(contract2.getRemind_state());
					toolEntity.setRemind_time(contractWarn2.getRemind_time());
					toolEntity.setRemind_name(contractWarn2.getRemind_name());
					toolEntity.setRemind_content(contractWarn2.getRemind_content());
					toolEntity.setRemind_contentAll(contractWarn2.getRemind_content());
					if ("Y".equals(personFlag)) {
						toolEntity.setWarnPersonFlag("Y");
					}
					toolEntities.add(toolEntity);
				}
			}
			return toolEntities;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/*
	 * 拆分新建页面传递过来的提醒人信息
	 * @return
	 * @author WQH
	 * @date 2016年7月15日
	 */
	private List<ContractWarn> getConWarnInfo(ContractWarn contractWarn, String remind_person, String remind_name) {
		List<ContractWarn> temp = Lists.newArrayList();
		String[] remind_persons = remind_person.split(",");
		String[] remind_names = remind_name.split(",");
		for(int i = 0;i<remind_persons.length;i++){
			ContractWarn tmp = new ContractWarn();
			tmp.setWarn_contractNo(contractWarn.getWarn_contractNo());
			tmp.setRemind_content(contractWarn.getRemind_content());
			tmp.setRemind_time(contractWarn.getRemind_time());
			tmp.setRemind_person(remind_persons[i]);
			tmp.setRemind_name(remind_names[i]);
			temp.add(tmp);
		}
		return temp;
	}
}


