/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.coolshow.jeesite.modules.cmag.conmanger.web;

import com.coolshow.frm.jeesite.common.beanvalidator.BeanValidators;
import com.coolshow.frm.jeesite.common.config.Global;
import com.coolshow.frm.jeesite.common.persistence.Page;
import com.coolshow.frm.jeesite.common.utils.DateUtils;
import com.coolshow.frm.jeesite.common.utils.IdGen;
import com.coolshow.frm.jeesite.common.utils.StringUtils;
import com.coolshow.frm.jeesite.common.utils.excel.ExportExcel;
import com.coolshow.frm.jeesite.common.utils.excel.ImportExcel;
import com.coolshow.frm.jeesite.common.web.BaseController;
import com.coolshow.frm.jeesite.modules.sys.dao.AreaDao;
import com.coolshow.frm.jeesite.modules.sys.entity.Area;
import com.coolshow.frm.jeesite.modules.sys.entity.Menu;
import com.coolshow.frm.jeesite.modules.sys.entity.Office;
import com.coolshow.frm.jeesite.modules.sys.entity.User;
import com.coolshow.frm.jeesite.modules.sys.service.SystemService;
import com.coolshow.frm.jeesite.modules.sys.utils.UserUtils;
import com.coolshow.jeesite.common.datagrid.DataRequest;
import com.coolshow.jeesite.common.datagrid.DataResponse;
import com.coolshow.jeesite.common.tool.*;
import com.coolshow.jeesite.common.tool.FormBean.Result;
import com.coolshow.jeesite.modules.cmag.conmanger.service.ConManagerDataService;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.*;
import com.coolshow.jeesite.modules.cmag.newcontract.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 合同维护管理
 * ClassName: ConMangerDataController
 * @author LJW
 * @date 2016年5月10日
 */
@Controller
@RequestMapping(value = "${adminPath}/cmag/conmanger/mag")
public class ConMangerDataController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ConMangerDataController.class);
	
	@Autowired
	private ConManagerDataService conManagerDataService;
	@Autowired
	private ConFollowService conFollowService;
	@Autowired
	private EnclosureService enclosureService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private DistributorService distributorService;
	@Autowired
	private ScanningService scanningService;
	@Autowired
	private RelationContractService relationContractService;
	@Autowired
	private ContractDataService contractDataService;
	@Autowired
	private ExtendSystemService extendSystemService;
	@Autowired
	private OtherService otherService;
	@Autowired
	private VerifyInfoService verifyInfoService;
	@Autowired
	private AreaDao areaDao;
	@Autowired
	private CheckContractLogService checkontractLogService;
	@Autowired
	private ContractStateService contractStateService;
	
	
	
	/**
	  * @Description: 显示查询列表页面
	  * @param  @param contract
	  * @param  @param request
	  * @param  @param response
	  * @param  @param model
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月20日
	 */
	@RequestMapping(value="showMainPage")
	public String showMainPage(Contract contract, HttpServletRequest request, HttpServletResponse response, Model model){
		try {
			String flag = request.getParameter("flag");
			String myCon = request.getParameter("myCon");
			model.addAttribute("flag", flag);
			model.addAttribute("myCon", myCon);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/cmag/conmanger/contractQueryMainPage";
	}
	
	@RequestMapping(value="showSubMainPage")
	public String showSubMainPage(Contract contract, HttpServletRequest request, HttpServletResponse response, Model model){
		try {
			String pre = request.getParameter("pre");
			model.addAttribute("pre",pre);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/cmag/conmanger/subQueryMainPage";
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
	@RequiresPermissions("cmag:conmanger:mag:view")
	@RequestMapping(value = {"list", ""})
	public String list(Contract contract, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			String flag = request.getParameter("flag");
			String myCon = request.getParameter("myCon");
			String tabType = request.getParameter("tabType");
			String contractNo = request.getParameter("contract_no");
			model.addAttribute("flag", flag);
			model.addAttribute("myCon", myCon);
			model.addAttribute("tabType", tabType);
			model.addAttribute("contractNo", contractNo);
		}catch (Exception e) {
			model.addAttribute("message", "初始化合同维护管理页面异常！");
		}
		return "modules/cmag/conmanger/contractQueryPage";
	}

	/**
	 * 获取所有合同状态对象json
	 * @param contract
	 * @param response
	 * @throws JsonProcessingException 
	 */
	@ResponseBody
	@RequestMapping("getConStatesJson")
	public String getConStatesJson() throws JsonProcessingException{
		List<ContractState> contractStates = contractStateService.getAllContractState();
		return new ObjectMapper().writeValueAsString(contractStates);
	}
	
	/**
	 * 获取当前合同状态代码
	 * @param contract
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getConState")
	public String getConState(Contract contract){
		contract = contractDataService.findContract(contract.getContract_no());
		return contract.getState_id();
	}
	/**
	 * 显示流程图页
	 * @param contract
	 * @param model
	 * @return
	 */
	@RequestMapping("showFlowChartPage")
	public String showFlowChartPage(Contract contract,Model model){
		model.addAttribute("contract", contract);
		return "modules/cmag/conmanger/flowchart";
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
	@SuppressWarnings("rawtypes")
    public DataResponse list1(@RequestParam(defaultValue="1",value="page") String page, @RequestParam(defaultValue="10",value="rows") String rows,  
    							Model model,HttpServletRequest httpServletRequest){  
		try {
			Contract contract = new Contract();
			DataRequest request = new DataRequest();
			String flag = httpServletRequest.getParameter("flag");
			String myCon = httpServletRequest.getParameter("myCon");
			String tabType = httpServletRequest.getParameter("tabType");
			String contract_no = httpServletRequest.getParameter("contract_no");
			String curUserRoleType = UserUtils.getRoleList().get(0).getRoleType();
			String curUserId = UserUtils.getUser().getId();
			contract = getRequestPara(httpServletRequest);
            request.setPage(StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page));  
            request.setRows(StringUtils.isEmpty(rows) ? 10 : Integer.valueOf(rows)); 
        	List<Menu> userMenus = UserTool.getUserOperBtn("合同维护管理");
            /**
             * 以下查询关联合同信息
             */
            if ("00".equals(tabType)) {
        		DataRequest dataRequest = new DataRequest();
        		RelationContract relationContract = new RelationContract();
        		relationContract.setContract_no(contract_no);
        		contract.setRelationcontract(relationContract);
        		List<Contract> conList = contractDataService.findRelationList(contract);
        		List<Contract> tmplistAll = Lists.newArrayList();
				for (Contract contractALLs:conList){
					User user_tmp = UserUtils.get(contractALLs.getCreate_user());
					contractALLs.setCreateUser_name(user_tmp.getName());
					contractALLs.setMenus(getSplitStr(userMenus));
					tmplistAll.add(contractALLs);
				}
        		model.addAttribute("tabType", tabType);
        		logger.warn("当前用户合同维护功能中拥有的操作权限为：【"+getSplitStr(userMenus)+"】");
        		return conManagerDataService.findLimitList(dataRequest,tmplistAll);
			}
            //判断是否为管理角色登录
            if("1".equals(curUserId) || "assignment".equals(curUserRoleType) || "security-role".equals(curUserRoleType)){
				List<Contract> contracts = contractDataService.findAllCon(contract);
				List<Contract> tmplistAll = Lists.newArrayList();
				for (Contract contractALLs:contracts){
					long counts = extendSystemService.findNotUpScan(contractALLs.getContract_no());			
					if(counts >= 1){
						contractALLs.setFileType(checkEncType(contractALLs));					
					}
					User user_tmp = UserUtils.get(contractALLs.getCreate_user());
					contractALLs.setCreateUser_name(user_tmp.getName());
					contractALLs.setMenus(getSplitStr(userMenus));
					tmplistAll.add(contractALLs);
				}
				if("Y".equals(myCon)){
					tmplistAll = statisticsInfo(tmplistAll,flag);
				}
				logger.warn("当前用户合同维护功能中拥有的操作权限为：【"+getSplitStr(userMenus)+"】");
				return conManagerDataService.search(request,tmplistAll);
			}
            ConFollow conFollow = new ConFollow();
			conFollow.setUser_id(UserUtils.getUser().getId());
			long tmp = conFollowService.findCowFollowCount(conFollow);
			List<Contract> conList = Lists.newArrayList(); 
			if(tmp >= 1){ //表示当前登录人 为跟踪人 
				conFollow.setUser_id(UserUtils.getUser().getId());
				contract.setConFollow(conFollow);
				contract.setCreate_user(UserUtils.getUser().getId());
				conList = conManagerDataService.findContracts(contract); 
			}else { //表示当前登录人 为拟定人
				contract.setCreate_user(UserUtils.getUser().getId());
				conList = conManagerDataService.findNewList(contract);
			}
			List<Contract> tmplistAll = Lists.newArrayList();
			if("Y".equals(myCon)){
				tmplistAll = statisticsInfo(conList,flag);
				List<Contract> tmp2 = Lists.newArrayList();
				for (Contract contractALLs:tmplistAll){
					long counts = extendSystemService.findNotUpScan(contractALLs.getContract_no());			
					if(counts >= 1){
						contractALLs.setFileType(checkEncType(contractALLs));					
					}
					User user_tmp = UserUtils.get(contractALLs.getCreate_user());
					contractALLs.setCreateUser_name(user_tmp.getName());
					contractALLs.setMenus(getSplitStr(userMenus));
					tmp2.add(contractALLs);
				}
				logger.warn("当前用户合同维护功能中拥有的操作权限为：【"+getSplitStr(userMenus)+"】");
				return conManagerDataService.findLimitList(request,tmp2);
			}
			List<Contract> tmp3 = Lists.newArrayList();
			for (Contract contractALLs:conList){
				long counts = extendSystemService.findNotUpScan(contractALLs.getContract_no());			
				if(counts >= 1){
					contractALLs.setFileType(checkEncType(contractALLs));					
				}
				User user_tmp = UserUtils.get(contractALLs.getCreate_user());
				contractALLs.setCreateUser_name(user_tmp.getName());
				contractALLs.setMenus(getSplitStr(userMenus));
				tmp3.add(contractALLs);
			}
			logger.warn("当前用户合同维护功能中拥有的操作权限为：【"+getSplitStr(userMenus)+"】");
			return conManagerDataService.findLimitList(request,tmp3);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null; 
	}
	
	/**
	 * @Description：查询我的合同链接数据
	 * @param pages
	 * @param flag
	 * @return
	 */
	private List<Contract> statisticsInfo(List<Contract> pages, String state_flag) {
		List<Contract> tmplist = Lists.newArrayList();
		if("00".equals(state_flag)){
			for(Contract contract1:pages){
				long counts = extendSystemService.findNotUpScan(contract1.getContract_no());			
				if(counts < 1 && "03".equals(contract1.getState_id())){
					tmplist.add(contract1);
				}
			}
		}else{
			for (Contract contract2 : pages) {
				if (state_flag.equals(contract2.getState_id())) {
					tmplist.add(contract2);
				}
			}
		}
		return tmplist;
	}

	/**
	  * @Description: 查询关联合同信息
	  * @param  @param contract
	  * @param  @param request
	  * @param  @param response
	  * @param  @param model
	  * @param  @return 
	  * @return String  
	 * @throws UnsupportedEncodingException 
	  * @throws
	  * @author LJW
	  * @date 2016年5月31日
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="listRelation")
	public DataResponse listRelation(@RequestParam(defaultValue="1",value="page") String page, @RequestParam(defaultValue="10",value="rows") String rows, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String contractNo = request.getParameter("contract_no");
		String tabType = request.getParameter("tabType");
		DataRequest dataRequest = new DataRequest();
		Contract contract = new Contract();
		dataRequest.setPage(StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page));  
		dataRequest.setRows(StringUtils.isEmpty(rows) ? 10 : Integer.valueOf(rows)); 
		contract.setContract_no(contractNo);
		RelationContract relationContract = new RelationContract();
		relationContract.setContract_no(contractNo);
		contract.setRelationcontract(relationContract);
		List<Contract> conList = contractDataService.findRelationList(contract);
		model.addAttribute("tabType", tabType);
		return conManagerDataService.findLimitList(dataRequest,conList);
	}
	
	@ResponseBody
	@RequestMapping(value = {"listData"})
	public Page<Contract> listData(Contract contract, HttpServletRequest request, HttpServletResponse response, Model mode){
		ConFollow conFollow = new ConFollow();
		conFollow.setUser_id(UserUtils.getUser().getId());
		long tmp = conFollowService.findCowFollowCount(conFollow);
		Page<Contract> page = new Page<Contract>();
		if(contract.getJfOffice() != null && contract.getYfOffice() != null){
			contract.setFirstParty(contract.getJfOffice().getId());
			contract.setSecondParty(contract.getYfOffice().getId());
		}
		if(tmp >= 1){ //表示当前登录人 为跟踪人 
			conFollow.setUser_id(UserUtils.getUser().getId());
			contract.setConFollow(conFollow);
			contract.setCreate_user(UserUtils.getUser().getId());
			page = conManagerDataService.findContracts(new Page<Contract>(request, response), contract); 
		}else { //表示当前登录人 为拟定人
			contract.setCreate_user(UserUtils.getUser().getId());
			page = conManagerDataService.findNewList(new Page<Contract>(request, response), contract); 
		}
		return page;
	}
	
	/**
	  * @Description: 查看和修改合同信息处理
	  * @param  @param contract
	  * @param  @param model
	  * @param  @param request
	  * @param  @return 
	  * @return String  
	 * @throws UnsupportedEncodingException 
	  * @throws
	  * @author L
	  * @date 2016年5月10日
	 */
	@RequestMapping(value = "form")
	public String form(Contract contract, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		String contractNo = request.getParameter("contract_no");
		String tabType = request.getParameter("tabType");	  //该标志表示当前页面是什么操作
		String warnFlag = request.getParameter("warnFlag");   //合同提醒查询页面传过来
		String contractTypeDm = request.getParameter("contract_type_dm");
		String enclosure_id = request.getParameter("enclosure_id");
		contract.setEnclosure_id(enclosure_id);
		contract.setContract_no(contractNo);
		contract.setContract_type_dm(contractTypeDm);
		User lxgUser = new User();
		RelationContract relationContract = new RelationContract();
		Office jfOffice = new Office();
		Office yfOffice = new Office();
		Area area = new Area();
		try {
			if("3".equals(tabType)){
				Enclosure enclosure = new Enclosure();
				enclosure.setEnclosure_id(contract.getEnclosure_id());
				enclosure.setFlag("02");
				Long enclosures = enclosureService.findEnclosureCount(enclosure);
				if (enclosures < 1) {
					redirectAttributes.addFlashAttribute("Flag", "N");
					addMessage(redirectAttributes, "您必须先上传扫描件，然后才可以进行生效确认操作！");
					return "redirect:"+Global.getAdminPath()+"/cmag/conmanger/mag/";
				}	
			}
			request.setAttribute("tabType", tabType);
			contract = conManagerDataService.findContractInfo(contract);
			//设置所属省份
			area.setId(contract.getArea_id());
			area = areaDao.findAreaName(area);
			String tmpAreaName = area.getName()+"|"+area.getShort_name();
			area.setName(tmpAreaName);
			contract.setArea(area);
			//设置履行跟踪人	
			ConFollow conFollow = new ConFollow();
			conFollow.setContract_no(contractNo);
			String[] lxrs = getConFollowIdName(conFollowService.findAllList(conFollow));
			lxgUser.setId(lxrs[0]);
			lxgUser.setName(lxrs[1]);
			contract.setLxgUser(lxgUser);
			//获取附件信息
			Enclosure enclosure = new Enclosure();
			enclosure.setFlag("01");
			enclosure.setEnclosure_id(contract.getEnclosure_id());
			enclosure = enclosureService.getEnclosure(enclosure);
			if (enclosure != null) {
				contract.setFj(enclosure.getPath());
				contract.setEnclosure(enclosure);
			}
			//合同甲方 乙方
			jfOffice.setId(contract.getFirstParty());
			jfOffice.setName(contract.getFirstPartyName());
			yfOffice.setId(contract.getSecondParty());
			yfOffice.setName(contract.getSecondPartyName());
			contract.setJfOffice(jfOffice);
			contract.setYfOffice(yfOffice);
			//关联合同
			relationContract.setContract_no(contract.getContract_no());
			contract.setRelationcontract(relationContract);
			//供前台判断是否显示查询关联合同按钮
			List<Contract> contracts = contractDataService.findRelationAllList(contract);
			if (contracts != null && contracts.size() > 0) {
				long count = 1;
				model.addAttribute("count", count);
			}
			String[] relaCon = getRealationCons(contractDataService.findRelationAllList(contract));
			relationContract.setContract_no(relaCon[0]);
			relationContract.setName(relaCon[1]);
			contract.setRelationcontract(relationContract);
		} catch (Exception e) {
			//e.printStackTrace();
			addMessage(redirectAttributes, "查询数据异常！"+e.getMessage());
			return "redirect:"+Global.getAdminPath()+"/cmag/conmanger/mag/";
		}
		model.addAttribute("contract", contract);
		model.addAttribute("warnFlag", warnFlag);
		return "modules/cmag/conmanger/contractForm";
	}

	/**
	  * @Description: 合同初审  查看初审信息
	  * @param  @param contract
	  * @param  @param model
	  * @param  @param request
	  * @param  @param redirectAttributes
	  * @param  @return 
	  * @return String  
	 * @throws UnsupportedEncodingException 
	  * @throws
	  * @author LJW
	  * @date 2016年6月13日
	 */
	@RequestMapping(value = "praeiudicium")
	public String praeiudicium(Contract contract, Model model,@RequestParam("contract_no")String contract_no,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException{
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/cmag/conmanger/mag/?repage";
		}
		request.setCharacterEncoding("utf-8");
		String contractNo = request.getParameter("contract_no");
		VerifyInfo verifyInfo = new VerifyInfo();
		verifyInfo.setContract_no(contractNo);
		contract.setContract_no(contractNo);
		Page<VerifyInfo> page = verifyInfoService.findVerifyInfos(new Page<VerifyInfo>(request, response), verifyInfo);
		contract = contractDataService.findContract(contract.getContract_no());
		verifyInfo.setContract_no_name(contract.getContract_no_name());
		model.addAttribute("contract", contract);
		model.addAttribute("page", page);
		return "modules/cmag/conmanger/praeiudiciumPage";
	}
	
	/**
	  * @Description: 初审通过和初审不通过
	  * @param  @param contract
	  * @param  @param model
	  * @param  @param request
	  * @param  @param response
	  * @param  @param redirectAttributes
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月14日
	 */
	@RequestMapping(value = "setConState")
	public String setConState(Contract contract, Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes){
		String btnFlag = request.getParameter("btnFlag");
		//合同初审通过
		if ("Y".equals(btnFlag)) {
			contract.setState_id(ContractStateEnum.SHORT_LIST.getIndex());
		}
		//合同初审不通过
		if ("N".equals(btnFlag)) {
			contract.setState_id(ContractStateEnum.PRAEIUDICIUM_FAILURE.getIndex());
		}
		VerifyInfo verifyInfo = contract.getVerifyInfo();
		verifyInfo.setContract_no(contract.getContract_no());
		verifyInfo.setContract_name(contract.getContract_name());
		verifyInfoService.save(verifyInfo);
		conManagerDataService.updateContractSate(contract);
		return "redirect:"+Global.getAdminPath()+"/cmag/conmanger/mag/praeiudicium?contract_no="+contract.getContract_no();
	}
	
	/**
	  * @Description: 查看上传附件和扫描件处理方法
	  * @param  @param contract
	  * @param  @param model
	  * @param  @param request
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author L
	  * @date 2016年5月10日
	 */
	@RequestMapping(value = "getEnclosure")
	public String getEnclosure(Contract contract,Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes){
		String tabType = request.getParameter("tabType");
		String flag = request.getParameter("flag");
		Enclosure enclosure = new Enclosure();
		enclosure.setEnclosure_id(contract.getEnclosure_id());
		enclosure.setFlag(flag);
		if (StringUtils.isBlank(contract.getEnclosure_id())) {
			addMessage(redirectAttributes, "您的合同没有附件信息！");
			return "redirect:"+Global.getAdminPath()+"/cmag/conmanger/mag/?repage";
		}
		Page<Enclosure> page = conManagerDataService.findEnclosures(new Page<Enclosure>(request, response), enclosure);
		if (page.getList().size() == 0 || page.getList() == null) {
			addMessage(redirectAttributes, "您还没有上传扫描信息，请先上传扫描信息，在进行查看！");
			return "redirect:" + Global.getAdminPath()+ "/cmag/conmanger/mag/?repage";
		}
		model.addAttribute("page", page);
		request.setAttribute("tabType", tabType);
		return "modules/cmag/conmanger/enclosureList";
	}
	/**
	 * 获取谁看过此合同信息
	 * @param contract
	 * @param model
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "getWhoCheck")
	public String getWhoCheck(Contract contract,Model model){
		//获取所有的checkcontractlog对象
		List<CheckContractLog> checkContractLogs = checkontractLogService.getAllEntityBYContractNo(contract.getContract_no());
		model.addAttribute("logs", checkContractLogs);
		return "modules/cmag/conmanger/checkContractLogList";
	}
	
	/**
	  * @Description: 修改 生效确认操作处理方法
	  * @param  @param contract
	  * @param  @param model
	  * @param  @param redirectAttributes
	  * @param  @param request
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@RequestMapping(value = "update")
	public String update(Contract contract,@RequestParam(value = "file", required = false)MultipartFile file, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception{
		String operatorType = request.getParameter("operatorType");
		String isUpload = request.getParameter("isUpload");
		String old_contract_no = request.getParameter("old_contract_no");
		contract.setOld_contract_no(old_contract_no);
		String copyInfo = request.getParameter("copyInfo");
		if (!beanValidator(model, contract)){
			return form(contract, model,request,redirectAttributes);
		}
		if("02".equals(operatorType)){ //生效确认操作
			String czFlag = request.getParameter("czFlag");
			if ("Y".equals(czFlag)) {
				contract.setState_id(ContractStateEnum.CONTRACT_EXECUTION.getIndex());
			}
			if ("N".equals(czFlag)) {
				contract.setState_id(ContractStateEnum.SCANS_FAILURE.getIndex());
			}
			conManagerDataService.updateContractSate(contract);
			addMessage(redirectAttributes, "已确认成功！");
		}
		if("01".equals(operatorType)){ //编辑操作
			String jfOfficeId = contract.getJfOffice().getId();
			String yfOfficeId = contract.getYfOffice().getId();
			String[] jfOfficeIds = StringTool.getSplitStr(jfOfficeId, "|");
			String[] yfOfficeIds = StringTool.getSplitStr(yfOfficeId, "|");
			contract.setFirstParty(jfOfficeIds[0]);
			contract.setFirstPartyName(contract.getJfOffice().getName());
			contract.setSecondParty(yfOfficeIds[0]);
			contract.setSecondPartyName(contract.getYfOffice().getName());
			contract.setQdr(contract.getQdr());
			contract.setQdrName(contract.getQdrName());
			contract.setArea_id(contract.getArea().getId());
			//设置邮寄地址
			String s_province = request.getParameter("s_province");
			String s_city = request.getParameter("s_city");
			String s_county = request.getParameter("s_county");
			contract.setMail_address(s_province+" "+s_city+" "+s_county);
			relationContractService.del(contract.getOld_contract_no());
			Enclosure enclosure = new Enclosure();
			enclosure.setEnclosure_id(contract.getEnclosure_id());
			enclosure.setFlag("01");
			//复制合同自动生成合同编号名称
			if("Y".equals(copyInfo)){
				String typeDm = contract.getContract_type_dm();
				String conName = "";
				if(ContractTypeEnum.SUPPLIER.getIndex().equals(typeDm)){
					conName = contract.getJfOffice().getName();
				}
				if(ContractTypeEnum.DISTRIBUTOR.getIndex().equals(typeDm)){
					conName = contract.getYfOffice().getName();
				}
				contract.setArea_id(contract.getArea().getId());
				String contractNo = contractDataService.findMaxContractNo(contract);
				if(contractNo == null){
					contractNo = "";
				}
				String area_jc = StringTool.getSplitStr(contract.getArea().getName(),"|")[1];
				String contract_no_name = UuidFactory.getContractNo(conName, typeDm, area_jc, contractNo);
				contract.setContract_no_name(contract_no_name);
				//自动生成合同编号
				contract.setContract_no(UuidFactory.getUUID());
				//改变复制合同状态为待初审
				contract.setState_id(ContractStateEnum.NEW.getIndex());
			}
			saveRelatinCon(contract);
			if ("Y".equals(isUpload)) { //表示编辑操作时 附件没有改变
				enclosure = enclosureService.getEnclosure(enclosure);
			}else{
				//更新附件
				String path = Global.getUserfilesBaseDir() + "scanning/";
				String fileName	= file.getOriginalFilename();
				File targetFile = new File(path,UserUtils.getUser().getId()+"-"+DateUtils.getDate("yyyyMMddHHmmss")+"-"+fileName);  
				String tragetFileName = targetFile.getName();
				FileTool.saveFile(file, targetFile);
				enclosure.setName(fileName);
				enclosure.setPath(path+tragetFileName);
			}
			enclosureService.updateEnclosure(enclosure);
			conManagerDataService.updateContractInfo(contract);
			//供应商
			if (ContractTypeEnum.SUPPLIER.getIndex().equals(contract.getContract_type_dm())) {
				Supplier supplier = contract.getSupplier();
				if("".equals(contract.getSupplier().getChecking_date())){
					supplier.setChecking_date(null);
				}
				if("".equals(contract.getSupplier().getPayment_date())){
					supplier.setPayment_date(null);
				}
				if("".equals(contract.getSupplier().getVerify_date())){
					supplier.setVerify_date(null);
				}
				supplier.setContract_no(contract.getContract_no());
				supplier.setCreate_time(contract.getCreate_time());
				supplier.setCreate_user(contract.getCreate_user());
				supplier.setOld_contract_no(contract.getOld_contract_no());
				supplierService.updateSupplier(supplier);
			}
			//分销商
			if (ContractTypeEnum.DISTRIBUTOR.getIndex().equals(contract.getContract_type_dm())) {
				Distributor distributor = contract.getDistributor();
				distributor.setContract_no(contract.getContract_no());
				distributor.setCreate_time(contract.getCreate_time());
				distributor.setCreate_user(contract.getCreate_user());
				distributor.setOld_contract_no(contract.getOld_contract_no());
				distributorService.updateDistributor(contract.getDistributor());
			}
			//其他
			if (ContractTypeEnum.QT.getIndex().equals(contract.getContract_type_dm())){
				Other other = contract.getOther();
				other.setContract_no(contract.getContract_no());
				other.setOld_contract_no(contract.getOld_contract_no());
				otherService.updateOther(contract.getOther());
			}
			conFollowService.delByConNo(old_contract_no);
			String uids = contract.getLxgUser().getId();
			List<ConFollow> temp = getConFollow(uids, contract.getContract_no());
			if(temp != null && temp.size() != 0){
				conFollowService.saveConFollow(temp);
			}
			addMessage(redirectAttributes, "合同信息修改成功！");
		}
		if("03".equals(operatorType)){ //保存扫面件
			contract.setState_id(ContractStateEnum.SCANS_CONFIRM.getIndex());
			String path = Global.getUserfilesBaseDir() + "scanning/";
			String fileName	= file.getOriginalFilename();
			File targetFile = new File(path,UserUtils.getUser().getId()+"-"+DateUtils.getDate("yyyyMMddHHmmss")+"-"+fileName);  
			FileTool.saveFile(file, targetFile);
			Scanning scanning = new Scanning();
			scanning.setContract_no(contract.getContract_no());
			scanning.setEnclosure_id(contract.getEnclosure_id());
			long counts = extendSystemService.findNotUpScan(contract.getContract_no());	
			if (counts < 1){
				scanningService.insertScanning(scanning);
				enclosureService.save(getEnclosure(fileName, contract.getEnclosure_id(), targetFile));
			}
			conManagerDataService.updateContractSate(contract);
			enclosureService.updateEnclosure(getEnclosure(fileName, contract.getEnclosure_id(), targetFile));
			addMessage(redirectAttributes, "合同扫描件上传成功！");
		}
		return "redirect:"+Global.getAdminPath()+"/cmag/conmanger/mag/";
	}
	
	/**
	  * @Description: 检查要查询的合同信息是否出现错误
	  * @param  @param contract
	  * @param  @param model
	  * @param  @param request
	  * @param  @return 
	  * @return FormBean  
	  * @throws
	  * @author LJW
	  * @date 2016年7月12日
	 */
	@ResponseBody
	@RequestMapping(value = "checkContract")
	public FormBean checkContract(Contract contract,Model model,HttpServletRequest request) {
		FormBean formBean = new FormBean();
		try {
			String contractNo = request.getParameter("contract_no");
			String contractTypeDm = request.getParameter("contract_type_dm");
			contract.setContract_no(contractNo);
			contract.setContract_type_dm(contractTypeDm);
			contract = conManagerDataService.findContractInfo(contract);
			if (contract == null) {
				formBean.setStatus(Result.FAILURE);
				formBean.setMsg("查询数据失败,请联系管理员查询具体失败的原因！");
				return formBean;
			}
			formBean.setStatus(Result.SUCCESS);
			return formBean;
		} catch (Exception e) {
			formBean.setStatus(Result.FAILURE);
			formBean.setMsg("查询数据异常：原因是【"+e.getMessage()+"】");
		}
		return null;
		
	}
	
	/**
	  * @Description: 复制合同信息
	  * @param  @param contract
	  * @param  @param model
	  * @param  @param request
	  * @param  @param redirectAttributes
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月6日
	 */
	@ResponseBody
	@RequestMapping(value = "copyContract")
	public FormBean copyContract(Contract contract,@RequestParam("contract_type_dm")String contract_type_dm, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		FormBean formBean = new FormBean();
		try {
			String contract_no = contract.getContract_no();
			String contract_no_name = contract.getContract_no_name();
			String temp = "-"+IdGen.randomBase62(3);
			String old_enclosureId = contract.getEnclosure_id();
			String new_enclosureId = UuidFactory.getUUID();
			int index = contract_no_name.indexOf("号");
			contract_no_name = contract_no_name.substring(0,index+1);
			contract = conManagerDataService.findContractInfo(contract);
			String contract_name = contract.getContract_name();
			int index2 = contract_name.indexOf("copy");
			if(index2 >= 0){
				contract_name = contract_name.substring(index2+4);
			}
			int index3 = contract_no.indexOf("-");
			if(index3 > 0){
				contract_no = contract_no.substring(0,index3);
			}
			contract.setContract_no(contract_no+temp);
			contract.setContract_no_name(contract_no_name+temp);
			contract.setContract_name("copy"+contract_name);
			contract.setEnclosure_id(new_enclosureId);
			contract.setState_id(ContractStateEnum.COPY.getIndex());
			contractDataService.saveContract(contract);
			//供应商
			if (ContractTypeEnum.SUPPLIER.getIndex().equals(contract_type_dm)) {
				contract.getSupplier().setSupplier_no(UuidFactory.getUUID());
				contract.getSupplier().setContract_no(contract.getContract_no());
				supplierService.save(contract.getSupplier());
			}
			//分销商
			if (ContractTypeEnum.DISTRIBUTOR.getIndex().equals(contract_type_dm)) {
				contract.getDistributor().setDistributor_no(UuidFactory.getUUID());
				contract.getDistributor().setContract_no(contract.getContract_no());
				distributorService.saveDistributorInfo(contract.getDistributor());
			}
			//其他
			if (ContractTypeEnum.QT.getIndex().equals(contract_type_dm)) {
				contract.getOther().setContract_no(contract.getContract_no());
				otherService.saveOtherInfo(contract.getOther());
			}
			//获取履行跟踪人，并且以新的合同编号，保存履行跟踪人
			ConFollow conFollow = new ConFollow();
			conFollow.setContract_no(contract_no);;
			List<ConFollow> cFollows = conFollowService.findAllList(conFollow);
			List<ConFollow> newFollows = Lists.newArrayList();
			for (ConFollow tmp:cFollows) {
				ConFollow newConFollow = new ConFollow();
				newConFollow.setCreateuser_id(tmp.getCreateuser_id());
				newConFollow.setUser_id(tmp.getUser_id());
				newConFollow.setContract_no(tmp.getContract_no()+temp);
				newFollows.add(newConFollow);
			}
			//保存附件
			Enclosure enclosure = new Enclosure();
			enclosure.setEnclosure_id(old_enclosureId);
			enclosure.setFlag("01");
			enclosure = enclosureService.getEnclosure(enclosure);
			enclosure.setEnclosure_id(new_enclosureId);
			enclosureService.save(enclosure);
			conFollowService.saveConFollow(newFollows);
			formBean.setStatus(Result.SUCCESS);
			formBean.setMsg("复制合同信息成功，请您对复制的合同信息进行修改和其他操作！");
			return formBean;
		} catch (Exception e) {
			e.printStackTrace();
			formBean.setStatus(Result.FAILURE);
			formBean.setMsg("复制合同信息失败：原因是【"+e.getMessage()+"】");
		}
		return null;
	}
	
	/**
	  * @Description: 导出合同信息
	  * @param  @param contract
	  * @param  @param request
	  * @param  @param response
	  * @param  @param redirectAttributes
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年5月19日
	 */
	@RequestMapping(value = "exportExcel", method=RequestMethod.POST)
    public String exportExcel(Contract contract,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String curUserRoleType = UserUtils.getRoleList().get(0).getRoleType();
			String curUserId = UserUtils.getUser().getId();
			ConFollow conFollow = new ConFollow();
			conFollow.setUser_id(UserUtils.getUser().getId());
			long tmp = conFollowService.findCowFollowCount(conFollow);
			Page<Contract> page = new Page<Contract>();
			if(contract.getJfOffice() != null && contract.getYfOffice() != null){
				contract.setFirstParty(contract.getJfOffice().getId());
				contract.setSecondParty(contract.getYfOffice().getId());
			}
			//表示当前登录人为查看全局数据角色
			if("1".equals(curUserId) || "assignment".equals(curUserRoleType) || "security-role".equals(curUserRoleType)){
				List<Contract> contracts = contractDataService.findAllCon(contract);
				page.setList(contracts);
			}else{
				if(tmp >= 1){ //表示当前登录人 为跟踪人 
					conFollow.setUser_id(UserUtils.getUser().getId());
					contract.setConFollow(conFollow);
					contract.setCreate_user(UserUtils.getUser().getId());
					page = conManagerDataService.findContracts(new Page<Contract>(request, response), contract); 
				}else { //表示当前登录人 为拟定人
					contract.setCreate_user(UserUtils.getUser().getId());
					page = conManagerDataService.findNewList(new Page<Contract>(request, response), contract); 
				}
			}
			List<Contract> contractTemp = page.getList();
			List<Contract> tmpList = Lists.newArrayList();
			for (Contract contracts:contractTemp){
				contracts.setSs_area(checkSsArae(contracts.getSs_area()));
				tmpList.add(contracts);
			}
			page.setList(tmpList);
            String fileName = "合同数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
    		new ExportExcel("合同数据", Contract.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出合同数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/cmag/conmanger/mag/list?repage";
    }
	
	/**
	  * @Description: 从服务器上下载附件
	  * @param  @param contract
	  * @param  @param request
	  * @param  @param response
	  * @param  @param redirectAttributes
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月12日
	 */
	@RequestMapping(value = "downloadFile")
	public void downloadFile(Contract contract,@RequestParam("flag")String flag,HttpServletRequest request, HttpServletResponse response){
		Enclosure enclosure = new Enclosure();
		enclosure.setEnclosure_id(contract.getEnclosure_id());
		enclosure.setFlag(flag);
		enclosure = enclosureService.getEnclosure(enclosure);
		String path = enclosure.getPath();
		try {
			FileTool.download(path, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断扫描件类型
	 * @param contractALLs
	 * @return
	 * @throws Exception 
	 */
	private String checkEncType(Contract contractALLs) throws Exception {
		Enclosure enclosure = new Enclosure();
		enclosure.setEnclosure_id(contractALLs.getEnclosure_id());
		enclosure.setFlag("02");
		String fileType = "";
		try{
			enclosure = enclosureService.getEnclosure(enclosure);
			String fileName = enclosure.getName();
			String type = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()).toLowerCase();
			String compressType = "rar,zip,tar";
			String picType = "jpeg,jpg,png";
			if (compressType.contains(type)){
				fileType = "01" ;
			}
			else if (picType.contains(type)){
				fileType = "02" ;
			}
			else if ("pdf".equals(type)){
				fileType = "03" ;
			}
			else{
				fileType = "04";
			}
		}catch(Exception e){
			throw e;
		}
		return fileType;
	}
	
	/**
	 * @Description 导出合同信息"所属区域"转义
	 * @param ss_area
	 * @return
	 * @author WQH
	 * @date 2016年6月6日
	 */
	private String checkSsArae(String ss_area) {
		int ss_areaInt = Integer.parseInt(ss_area);
		String ssName = "";
		switch(ss_areaInt){
		case 01 : ssName = "华南地区" ; break ;
		case 02 : ssName = "华东地区" ; break ;
		case 03 : ssName = "华中地区" ; break ;
		case 04 : ssName = "华北地区" ; break ;
		case 05 : ssName = "西南地区" ; break ;
		case 06 : ssName = "西北地区" ; break ;
		case 07 : ssName = "东北地区" ; break ;
		}
		return ssName;
	}

	/**
	 * 导入合同数据数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + Global.getAdminPath() + "/cmag/conmanger/mag/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel excel = new ImportExcel(file, 1, 0);
			try {
				if (file.getOriginalFilename().contains("合同基本信息数据")) {
					List<Contract> contracts = excel.getDataList(Contract.class);
					List<Contract> cList = Lists.newArrayList();
					for (Contract contract : contracts) {
						contract.setEnclosure_id(UuidFactory.getUUID());
						contract.setCreate_time(DateTool.uf_DateTimeToShortString(new Date()));
						contract.setCreate_user(UserUtils.getUser().getId());
						contract.setModify_time(DateTool.uf_DateTimeToShortString(new Date()));
						contract.setModify_user(UserUtils.getUser().getName());
						BeanValidators.validateWithException(validator,contract);
						cList.add(contract);
						successNum++;
					}
					contractDataService.insertList(cList);
				}
				if (file.getOriginalFilename().contains("供应商数据")) {
					List<Supplier> suppliers = excel.getDataList(Supplier.class);
					List<Supplier> sList = Lists.newArrayList();
					for (Supplier supplier : suppliers) {
						BeanValidators.validateWithException(validator, supplier);
						sList.add(supplier);
						successNum++;
					}
					supplierService.insertList(sList);
				}
				if (file.getOriginalFilename().contains("分销商数据")) {
					List<Distributor> distributors = excel.getDataList(Distributor.class);
					List<Distributor> dList = Lists.newArrayList();
					for (Distributor distributor : distributors) {
						BeanValidators.validateWithException(validator, distributor);
						dList.add(distributor);
						successNum++;
					}
					distributorService.insertList(dList);
				}
			} catch (ConstraintViolationException ex) {
				failureMsg.append("<br/>导入合同数据失败：");
				List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
				for (String message : messageList) {
					failureMsg.append(message + "; ");
					failureNum++;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				failureNum++;
				failureMsg.append("<br/>导入合同数据失败：" + ex.getMessage());
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条合同信息"+failureMsg);
		}catch(Exception e){
			e.printStackTrace();
			addMessage(redirectAttributes, "导入合同数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/cmag/conmanger/mag/list?repage";
	}
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "import/template")
    public String importFileTemplate(Contract contract,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String  flag = request.getParameter("flag");
			String fileName = "";
			if ("1".equals(flag)) {
				fileName = "合同基本信息数据导入模板.xlsx";
		    	List<Contract> list = Lists.newArrayList(); 
		    	new ExportExcel("合同数据", Contract.class,2).setDataList(list).write(response, fileName).dispose();
			}
			if ("2".equals(flag)) {
				fileName = "供应商数据导入模板.xlsx";
		    	List<Supplier> list = Lists.newArrayList(); 
		    	new ExportExcel("供应商数据", Supplier.class,2).setDataList(list).write(response, fileName).dispose();
			}
			if ("3".equals(flag)) {
				fileName = "分销商数据导入模板.xlsx";
		    	List<Distributor> list = Lists.newArrayList(); 
		    	new ExportExcel("分销商数据", Distributor.class,2).setDataList(list).write(response, fileName).dispose();
			}
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/cmag/conmanger/mag/list?repage";
    }
	
    /**
      * @Description: 遍历查询到的合同履行跟踪人 把所有的id 组装成一个字符串
      * @param  @param conList
      * @param  @return 
      * @return String[]  
      * @throws
      * @author LJW
      * @date 2016年5月18日
     */
    private String[] getConFollowIdName(List<ConFollow> conList) {
    	String[] tmStrings = new String[2];
    	StringBuffer ids = new StringBuffer();
    	StringBuffer names = new StringBuffer();
    	if(conList != null && conList.size() != 0){
    		for (ConFollow conFollow : conList) {
        		ids.append(conFollow.getUser_id()).append(",");
        		names.append(systemService.getUser(conFollow.getUser_id()).getName()).append(",");
    		}
    		ids = ids.deleteCharAt(ids.length() - 1);
        	names = names.deleteCharAt(names.length() - 1);
        	tmStrings[0] = ids.toString();
        	tmStrings[1] = names.toString();
    	}
		return tmStrings;
	}
    
    /**
      * @Description: 遍历查询到的关联合同信息 组装成一个字符串
      * @param  @param findRelationAllList
      * @param  @return 
      * @return String[]  
      * @throws
      * @author LJW
      * @date 2016年6月7日
     */
	private String[] getRealationCons(List<Contract> relationList) {
		String[] tmStrings = new String[2];
    	StringBuffer contractNos = new StringBuffer();
    	StringBuffer contractNames = new StringBuffer();
    	if(relationList != null && relationList.size() != 0){
    		for (Contract contract : relationList) {
    			contractNos.append(contract.getContract_no()).append(",");
    			contractNames.append(contract.getContract_no_name()+"|"+contract.getContract_name()).append(",");
    		}
    		contractNos = contractNos.deleteCharAt(contractNos.length() - 1);
    		contractNames = contractNames.deleteCharAt(contractNames.length() - 1);
        	tmStrings[0] = contractNos.toString();
        	tmStrings[1] = contractNames.toString();
    	}
		return tmStrings;
	}
    
    /**
      * @Description: 把上传的附件进行拆分，组装成附件实体列表
      * @param  @param scanningFj 
      * @return void  
      * @throws
      * @author LJW
      * @date 2016年5月19日
     */
    @SuppressWarnings("unused")
	private List<Enclosure> getEnclosures(String scanningFj,String enclosure_id) {
    	List<Enclosure> enclosures = Lists.newArrayList();
    	if(scanningFj == null || "".equals(scanningFj)){
    		return enclosures;
    	}
    	String[] fjs = scanningFj.split("\\|");
    	for(int i = 0;i < fjs.length;i++){
    		Enclosure enclosure = new Enclosure();
    		enclosure.setEnclosure_id(enclosure_id);
    		enclosure.setPath(fjs[i]);
    		String fjName = fjs[i].substring(fjs[i].lastIndexOf("/")+1,fjs[i].length());
    		enclosure.setName(fjName);
    		enclosure.setCreate_user(UserUtils.getUser().getName());
    		enclosure.setUpload_time(new Date());
    		enclosure.setFlag("02");
    		enclosures.add(enclosure);
    	}
    	return enclosures;
  	}
    
    /**
      * @Description: 得到附件实体
      * @param  @param scanningFj
      * @param  @param enclosure_id
      * @param  @return 
      * @return Enclosure  
      * @throws
      * @author LJW
      * @date 2016年6月2日
     */
	private Enclosure getEnclosure(String fileName,String enclosure_id,File targetFile) {
		Enclosure enclosure = new Enclosure();
		String path = Global.getUserfilesBaseDir() + "scanning/";
		enclosure.setEnclosure_id(enclosure_id);
		enclosure.setName(fileName);
		enclosure.setPath(path+targetFile.getName());
		enclosure.setCreate_user(UserUtils.getUser().getName());
		enclosure.setUpload_time(new Date());
		enclosure.setFlag("02");
    	return enclosure;
  	}
    
	//保存关联合同信息
	private void saveRelatinCon(Contract contract) {
		List<RelationContract> rContracts = Lists.newArrayList();
		if (contract.getRelationcontract().getContract_no() == null) {
			return;
		}
		String[] contractNos = contract.getRelationcontract().getContract_no().split(",");
		for (int i = 0; i < contractNos.length; i++) {
			RelationContract relationContract = new RelationContract();
			relationContract.setContract_no(contract.getContract_no());
			relationContract.setRelation_no(contractNos[i]);
			rContracts.add(relationContract);
		}
		relationContractService.insertList(rContracts);
	}
	
	/**
	 * @Description: 分隔前台传递过来的履行跟踪人数组，进行分割，并组装成List
	 * @param @param uids
	 * @param @param contract_no
	 * @param @return   
	 * @return List<ConFollow>  
	 * @throws
	 * @author LJW
	 * @date 2016年5月12日
	 */
	private List<ConFollow> getConFollow(String uids,String contract_no){
		List<ConFollow> conFollows = Lists.newArrayList();
		String[] ids = uids.split(",");
		Contract contract = contractDataService.findContract(contract_no);
		String createuser_id = contract.getCreate_user();
		for(int i = 0; i < ids.length; i++){
			ConFollow conFollow = new ConFollow();
			conFollow.setContract_no(contract_no);
			conFollow.setUser_id(ids[i]);
			long count = conFollowService.findRecord(conFollow);
			if(count < 1){
				conFollow.setCreateuser_id(createuser_id);
				conFollows.add(conFollow);
			}
		}
		return conFollows;
	}
	
	/**
	 * @Description 发送审核更新合同状态
	 * @param contract
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 * @author WQH
	 * @date 2016年6月15日
	 */
	@ResponseBody
	@RequestMapping(value = "sendToVerify")
	public FormBean sendToVerify(Contract contract, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request){
		FormBean formBean = new FormBean();
		String contractNo = request.getParameter("contract_no");
		try {
			contract.setContract_no(contractNo);
			contract.setState_id(ContractStateEnum.PRAEIUDICIUM.getIndex());
			int updateCount = conManagerDataService.updateContractSate(contract);
			if (updateCount >= 1) {
				formBean.setStatus(Result.SUCCESS);
				formBean.setMsg("该合同发送初审成功，已进入初审状态！");
			}
			return formBean;
		} catch (Exception e) {
			formBean.setStatus(Result.FAILURE);
			formBean.setMsg("该合同发送初审失败请重新发送初审：原因是【"+e.getMessage()+"】");
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
		String contractNoName = httpServletRequest.getParameter("contractNoName");
		String ssArea = httpServletRequest.getParameter("ssArea");
		String qdDate = httpServletRequest.getParameter("qdDate");
		String beginDate = httpServletRequest.getParameter("beginDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String qdName = httpServletRequest.getParameter("qdName");
		contract.setFirstParty(firstParty);
		contract.setSecondParty(secondParty);
		contract.setContract_no_name(contractNoName);
		contract.setContract_name(contractName);
		contract.setSs_area(ssArea);
		contract.setQd_date(qdDate);
		contract.setBegin_date(beginDate);
		contract.setEnd_date(endDate);
		contract.setQdrName(qdName);
		return contract;
	}
	
	/*
	 * @Description: 保存合同归档信息
	  * @throws
	  * @author WQH
	  * @date 2016年7月5日 
	 */
	@RequestMapping(value = "saveArcInfo")
	public String saveArcInfo(Contract contract, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request){
		if ("".equals(contract.getReceipt_time())){
			contract.setReceipt_time(null); 
		}
		if ("0".equals(contract.getOriginal_file())){
			contract.setOriginal_file(null); 
		}
		contractDataService.updateArchivedInfo(contract);
		model.addAttribute("contract", contract);
		addMessage(redirectAttributes, "合同归档信息保存成功！");
		return "redirect:"+Global.getAdminPath()+"/cmag/conmanger/mag/";
	}
	
	@SuppressWarnings("unused")
	private String getSplitStr(List<Menu> menus){
		StringBuffer str = new StringBuffer();
		for(Menu menu:menus){
			str.append(menu.getName()).append(",");
		}
		str.deleteCharAt(str.length()-1);
		return str.toString();
	}

	public CheckContractLogService getCheckontractLogService() {
		return checkontractLogService;
	}

	public void setCheckontractLogService(CheckContractLogService checkontractLogService) {
		this.checkontractLogService = checkontractLogService;
	}
	
	
}