/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.coolshow.jeesite.modules.cmag.newcontract.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coolshow.frm.jeesite.common.config.Global;
import com.coolshow.frm.jeesite.common.persistence.Page;
import com.coolshow.frm.jeesite.common.web.BaseController;
import com.coolshow.frm.jeesite.common.utils.DateUtils;
import com.coolshow.frm.jeesite.common.utils.StringUtils;
import com.coolshow.frm.jeesite.modules.sys.entity.Area;
import com.coolshow.frm.jeesite.modules.sys.entity.Office;
import com.coolshow.frm.jeesite.modules.sys.entity.User;
import com.coolshow.frm.jeesite.modules.sys.service.AreaService;
import com.coolshow.frm.jeesite.modules.sys.service.OfficeService;
import com.coolshow.frm.jeesite.modules.sys.service.SystemService;
import com.coolshow.frm.jeesite.modules.sys.utils.UserUtils;
import com.coolshow.jeesite.common.tool.ContractStateEnum;
import com.coolshow.jeesite.common.tool.ContractTypeEnum;
import com.coolshow.jeesite.common.tool.DateTool;
import com.coolshow.jeesite.common.tool.FileTool;
import com.coolshow.jeesite.common.tool.FormBean;
import com.coolshow.jeesite.common.tool.FormBean.Result;
import com.coolshow.jeesite.common.tool.StringTool;
import com.coolshow.jeesite.common.tool.UuidFactory;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ConFollow;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ContractType;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.DistributorBase;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Enclosure;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ListTool;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Other;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.RelationContract;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Supplier;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.SupplierBase;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.TopContract;
import com.coolshow.jeesite.modules.cmag.newcontract.service.ConFollowService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.ContractDataService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.DistributorBaseService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.DistributorService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.EnclosureService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.OtherService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.RelationContractService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.SupplierBaseService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.SupplierService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.TopContactsService;
import com.coolshow.jeesite.modules.cmag.util.DmTypeUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 合同拟定控制类
 * @Description TODO
 * ClassName: ContractDataController.java
 * @author LJW
 * @date 上午9:33:56
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping(value = "${adminPath}/cmag/newcontract/contractData")
public class ContractDataController extends BaseController {

	@Autowired
	private ContractDataService contractDataService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private DistributorService distributorService;
	@Autowired
	private EnclosureService enclosureService;
	@Autowired
	private ConFollowService conFollowService;
	@Autowired
	private TopContactsService topContactsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SupplierBaseService supplierBaseService;
	@Autowired
	private DistributorBaseService distributorBaseService;
	@Autowired
	private RelationContractService relationContractService;
	@Autowired
	private OtherService otherService;
	@Autowired
	private AreaService areaService;
	
	@ModelAttribute("contract")
	public Contract get(@RequestParam(required=false) String id) {
		Contract entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = contractDataService.get(id);
		}
		if (entity == null){
			entity = new Contract();
		}
		return entity;
	}
	
	@RequiresPermissions("cmag:newcontract:contractData:view")
	@RequestMapping(value = {"list", ""})
	public String list(Contract testData, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/cmag/newcontract/contractTypePage";
	}

	/**
	 * @Description: 保存合同信息
	 * @param @param contract
	 * @param @param model
	 * @param @param redirectAttributes
	 * @param @return   
	 * @return String  
	 * @throws Exception 
	 * @throws
	 * @author LJW
	 * @date 2016年5月10日
	 */
	@RequiresPermissions("cmag:newcontract:contractData:edit")
	@RequestMapping(value = "save")
	public String save(Contract contract,@RequestParam(value = "file", required = false)MultipartFile file, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception {
		String btnFlag = request.getParameter("btnFlag");
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/cmag/newcontract/contractData/";
		}
		String typeDm = request.getParameter("typeDm");
		if (!beanValidator(model, contract)){
			return "redirect:" + adminPath + "/cmag/newcontract/contractData/";
		}
		//合同类型
		ContractType contractType = new ContractType();
		contractType.setTypeDm(typeDm);
		contractType.setTypeName(ContractTypeEnum.getTypeName(typeDm));
		contract.setContractType(contractType);
		//自动生成合同编号
		contract.setContract_no(UuidFactory.getUUID());
		//自动生成合同编号名称
		contract.setContract_type_dm(typeDm);
		String conName = "";
		if(ContractTypeEnum.SUPPLIER.getIndex().equals(typeDm)){
			conName = contract.getYfOffice().getName();
		}
		if(ContractTypeEnum.DISTRIBUTOR.getIndex().equals(typeDm)){
			conName = contract.getJfOffice().getName();
		}
		if (ContractTypeEnum.QT.getIndex().equals(typeDm)){
			conName = "其他合同信息";
		}
		//保存合同基本信息
		String jfOfficeId = contract.getJfOffice().getId();
		String yfOfficeId = contract.getYfOffice().getId();
		String[] jfOfficeIds = StringTool.getSplitStr(jfOfficeId, "|");
		String[] yfOfficeIds = StringTool.getSplitStr(yfOfficeId, "|");
		contract.setFirstParty(jfOfficeIds[0]);
		contract.setFirstPartyName(contract.getJfOffice().getName());
		contract.setSecondParty(yfOfficeIds[0]);
		contract.setSecondPartyName(contract.getYfOffice().getName());
		contract.setQdr(contract.getQdrUser().getId());
		contract.setQdrName(contract.getQdrUser().getName());
		contract.setArea_id(contract.getArea().getId());
		//获取自动生成的合同标号
		String contractNo = contractDataService.findMaxContractNo(contract);
		String area_jc = StringTool.getSplitStr(contract.getArea().getName(),"|")[1];
		String contract_no_name = UuidFactory.getContractNo(conName, typeDm, area_jc, contractNo);
		contract.setContract_no_name(contract_no_name);
		//以下判断  创建合同完后 是单击时“保存”还是“保存并发送初审”
		if("01".equals(btnFlag)){
			contract.setState_id(ContractStateEnum.PRAEIUDICIUM.getIndex());
		}else {
			contract.setState_id(ContractStateEnum.NEW.getIndex());
		}
		contract.setUser_id(UserUtils.getUser().getId());
		contract.setCreate_time(DateTool.uf_DateTimeToShortString(new Date()));
		contract.setCreate_user(UserUtils.getUser().getId());
		contract.setModify_time(DateTool.uf_DateTimeToShortString(new Date()));
		contract.setModify_user(UserUtils.getUser().getName());
		contract.setEnclosure_id(UuidFactory.getUUID());
		//附件信息
		String path = Global.getUserfilesBaseDir() + "fj/";
		String fileName	= file.getOriginalFilename();
		File targetFile = new File(path,UserUtils.getUser().getId()+"-"+DateUtils.getDate("yyyyMMddHHmmss")+"-"+fileName);  
		FileTool.saveFile(file, targetFile);
		contract.setEnclosure(new Enclosure());
		contract.getEnclosure().setName(fileName);
		contract.getEnclosure().setPath(path+targetFile.getName());
		contract.getEnclosure().setCreate_user(UserUtils.getUser().getName());
		contract.getEnclosure().setUpload_time(new Date());
		contract.getEnclosure().setEnclosure_id(contract.getEnclosure_id());
		contract.getEnclosure().setFlag("01");
		//供应商
		if (ContractTypeEnum.SUPPLIER.getIndex().equals(typeDm)) {
			contract.getSupplier().setSupplier_no(UuidFactory.getUUID());
			contract.getSupplier().setContract_no(contract.getContract_no());
			if (StringUtils.isBlank(contract.getSupplier().getChecking_date())) {
				contract.getSupplier().setChecking_date(null);
			}
			if (StringUtils.isBlank(contract.getSupplier().getPayment_date())) {
				contract.getSupplier().setPayment_date(null);
			}
			if (StringUtils.isBlank(contract.getSupplier().getVerify_date())){
				contract.getSupplier().setVerify_date(null);
			}
			contract.getSupplier().setCreate_user(UserUtils.getUser().getName());
			contract.getSupplier().setCreate_time(DateTool.getLongDate(new Date()));
			contract.getSupplier().setModify_user(UserUtils.getUser().getName());
			contract.getSupplier().setModify_time(DateTool.getLongDate(new Date()));
			supplierService.save(contract.getSupplier());
		}
		//分销商
		if (ContractTypeEnum.DISTRIBUTOR.getIndex().equals(typeDm)) {
			contract.getDistributor().setDistributor_no(UuidFactory.getUUID());
			contract.getDistributor().setContract_no(contract.getContract_no());
			if (StringUtils.isBlank(contract.getDistributor().getChecking_date())) {
				contract.getDistributor().setChecking_date(null);
			}
			if (StringUtils.isBlank(contract.getDistributor().getPayment_date())) {
				contract.getDistributor().setPayment_date(null);
			}
			String protocol_type = contract.getDistributor().getProtocol_type();
			if (!StringUtils.isBlank(protocol_type) && !"00".equals(protocol_type)) {
				protocol_type = protocol_type.substring(1,protocol_type.length());
			}
			contract.getDistributor().setProtocol_type(protocol_type);
			contract.getDistributor().setCreate_user(UserUtils.getUser().getName());
			contract.getDistributor().setCreate_time(DateTool.getLongDate(new Date()));
			contract.getDistributor().setModify_user(UserUtils.getUser().getName());
			contract.getDistributor().setModify_time(DateTool.getLongDate(new Date()));
			distributorService.saveDistributorInfo(contract.getDistributor());
		}
		//其他
		if (ContractTypeEnum.QT.getIndex().equals(typeDm)){
			contract.getOther().setContract_no(contract.getContract_no());
			otherService.saveOtherInfo(contract.getOther());
		}
		//判断合同的日期类型的数据是否是""，是的强制设置为null，防止抛出异常
		contract = setDateTypeByNULL(contract);
		//设置邮寄地址
		String s_province = request.getParameter("s_province");
		String s_city = request.getParameter("s_city");
		String s_county = request.getParameter("s_county");
		contract.setMail_address(s_province+" "+s_city+" "+s_county);
		contractDataService.saveContract(contract);
		//保存关联合同信息
		saveRelatinCon(contract);
		//保存履行跟踪人   
		saveConFollow(contract);
		enclosureService.save(contract.getEnclosure());
		User qdrUser = new User();
		User lxrUser = new User();
		qdrUser.setId(contract.getQdrUser().getId());
		qdrUser.setName(contract.getQdrUser().getName());
		lxrUser.setId(contract.getLxgUser().getId());
		lxrUser.setName(contract.getLxgUser().getName());
		contract.setQdrUser(qdrUser);
		contract.setLxgUser(lxrUser);
		//合同甲方 乙方
		Office jfOffice = new Office();
		Office yfOffice = new Office();
		jfOffice.setId(contract.getFirstParty());
		jfOffice.setName(contract.getFirstPartyName());
		yfOffice.setId(contract.getSecondParty());
		yfOffice.setName(contract.getSecondPartyName());
		//关联合同
		RelationContract relationcontract = new RelationContract();
		relationcontract.setContract_no(contract.getRelationcontract().getContract_no());
		relationcontract.setName(contract.getRelationcontract().getName());
		contract.setRelationcontract(relationcontract);
		
		model.addAttribute("fileName", fileName);
		model.addAttribute("relationcontract", relationcontract);
		model.addAttribute("jfOffice", jfOffice);
		model.addAttribute("yfOffice", yfOffice);
		model.addAttribute("qdrUser", qdrUser);
		model.addAttribute("lxgUser", lxrUser);
		model.addAttribute("area",contract.getArea());
		model.addAttribute("contract", contract);
		model.addAttribute("message","成功保存合同信息，请您到合同维护列表进行相关操作！");
		return "modules/cmag/newcontract/newContractPage";
	}
	
	/**
	  * @Description: 合同类型页面处理方法
	  * @param  @param contract
	  * @param  @param model
	  * @param  @param request
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@RequiresPermissions("cmag:newcontract:contractData:edit")
	@RequestMapping(value = "contractByType")
	public String contractByType(Contract contract,Model model,HttpServletRequest request,@RequestParam("contractTypeDm")String contractTypeDm){
		ContractType contractType = new ContractType();
		if(ContractTypeEnum.SUPPLIER.getIndex().equals(contractTypeDm)){
			contractType.setTypeName(ContractTypeEnum.SUPPLIER.getName());
			contractType.setTypeDm(ContractTypeEnum.SUPPLIER.getIndex());
			contract.setContractType(contractType);
		}
		if(ContractTypeEnum.DISTRIBUTOR.getIndex().equals(contractTypeDm)){
			contractType.setTypeName(ContractTypeEnum.DISTRIBUTOR.getName());
			contractType.setTypeDm(ContractTypeEnum.DISTRIBUTOR.getIndex());
			contract.setContractType(contractType);
		}
		if(ContractTypeEnum.QT.getIndex().equals(contractTypeDm)){
			contractType.setTypeName(ContractTypeEnum.QT.getName());
			contractType.setTypeDm(ContractTypeEnum.QT.getIndex());
			contract.setContractType(contractType);
		}
		//把设置的常用联系人 默认设置为履行跟踪人
		User lxgUser = new User();
		TopContract topContract = new TopContract();
		topContract.setUser_id(UserUtils.getUser().getId());
		String[] lxrs = getTopContactsIdName(topContactsService.findList(topContract));
		if(lxrs.length == 0 || lxrs == null){
			model.addAttribute("contract",contract);
			return "modules/cmag/newcontract/newContractPage";
		}
		lxgUser.setId(lxrs[0]);
		lxgUser.setName(lxrs[1]);
		contract.setLxgUser(lxgUser);
		model.addAttribute("lxgUser",lxgUser);
		model.addAttribute("contract",contract);
		return "modules/cmag/newcontract/newContractPage";
	}
	
	
	/**
	  * @Description: 合同跟踪界面查询常用联系人
	  * @param  @param contract 
	  * @return String  
	  * @throws	
	  * @author WQH
	  * @date 2016年5月24日
	 */
	@RequestMapping(value = "topContByFind")
	public String topContByFind (Contract contract,Model model,HttpServletRequest request){
		User followStaff = new User();
		User draftStaff = new User();
		if("1".equals(UserUtils.getUser().getId())){
			draftStaff.setId(contract.getDraftStaff().getId());
			draftStaff.setName(contract.getDraftStaff().getName());
		}else{
			draftStaff.setId(request.getParameter("loginUserId"));
			draftStaff.setName(request.getParameter("loginUserName"));
		}
		TopContract topContract = new TopContract();
		//topContract.setUser_id(contract.getDraftStaff().getId());
		topContract.setUser_id(draftStaff.getId());
		String[] followStaffs = getTopContactsIdName(topContactsService.findList(topContract));
		if(followStaffs.length == 0 || followStaffs == null){
			model.addAttribute("contract",contract);
			return "modules/cmag/newcontract/topContracts";
		}
		followStaff.setId(followStaffs[0]);
		followStaff.setName(followStaffs[1]);
		contract.setFollowStaff(followStaff);
		
		model.addAttribute("followStaff",followStaff);
		model.addAttribute("draftStaff", draftStaff);
		model.addAttribute("contract",contract);
		model.addAttribute("flag", "Y");
		return "modules/cmag/newcontract/topContracts";
	}
	
	/**
	  * @Description: 用于设置常用联系人
	  * @param  @param user
	  * @param  @param model
	  * @param  @param request
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author L
	  * @date 2016年5月10日
	 */
	@RequiresPermissions("cmag:newcontract:contractData:view")
	@RequestMapping(value = "topContracts")
	public String topContracts(Contract contract,Model model,HttpServletRequest request){
		return "modules/cmag/newcontract/topContracts";
	}
	
	/**
	 * 设置常用联系人信息
	 * @param user
	 * @param model
	 * @return
	 * @author WQH
	 * @date 2016年5月17日
	 */
	@RequiresPermissions("cmag:newcontract:contractData:edit")
	@RequestMapping(value="insTopCon")
	public String insTopCon (Contract contract, Model model, HttpServletRequest request){
		TopContract topContract = new TopContract();
		topContract.setUser_id(request.getParameter("loginUserId"));
		String[] lxrs = getTopContactsIdName(topContactsService.findList(topContract));
		if(lxrs[0] == null && lxrs[1] == null){
			topContactsService.insTopCon(getTopContract(topContract.getUser_id(), contract.getFollowStaff().getId()));
			//topContactsService.topConInsTrac(getConFollows(topContract.getUser_id(), contract.getFollowStaff().getId()));
			model.addAttribute("message", "信息保存成功");
		}else{
			 //1 insert 用设置的跟踪人和查询出来的常用联系人比较
			String lxgzrIds = contract.getFollowStaff().getId();
			String[] lxgzrID = lxgzrIds.split(",");
			List<ConFollow> conFollowsAdd = Lists.newArrayList();
			List<TopContract> topContractsAdd = Lists.newArrayList();
			for (int i = 0; i < lxgzrID.length; i++) {
				if (!lxrs[0].contains(lxgzrID[i])) {
					ConFollow conFollowAdd = new ConFollow();
					TopContract topContractAdd = new TopContract();
					conFollowAdd.setCreateuser_id(topContract.getUser_id());
					conFollowAdd.setUser_id(lxgzrID[i]);
					conFollowAdd.setContract_no("");
					conFollowsAdd.add(conFollowAdd);
					topContractAdd.setUser_id(topContract.getUser_id());
					topContractAdd.setSecond_id(lxgzrID[i]);
					topContractsAdd.add(topContractAdd);
				}
			}
			if(conFollowsAdd.size() != 0 && topContractsAdd.size() != 0){
				//topContactsService.topConInsTrac(conFollowsAdd);
				topContactsService.insTopCon(topContractsAdd);
			}
			// 2 del 用查询出来的常用联系人和设置的跟踪人比较
			String[] lxrIds = lxrs[0].split(","); 
			List<ConFollow> conFollowsDel = Lists.newArrayList();
			List<TopContract> topContractsDel = Lists.newArrayList();
			for (int i = 0; i < lxrIds.length; i++) {
				if (!lxgzrIds.contains(lxrIds[i])) {
					ConFollow conFollowDel = new ConFollow();
					TopContract topContractDel = new TopContract();
					conFollowDel.setContract_no("");
					conFollowDel.setUser_id(lxrIds[i]);
					conFollowDel.setCreateuser_id(topContract.getUser_id());
					topContractDel.setUser_id(topContract.getUser_id());
					topContractDel.setSecond_id(lxrIds[i]);
					conFollowsDel.add(conFollowDel);
					topContractsDel.add(topContractDel);
				}
			}
			if (conFollowsDel.size() != 0 && topContractsDel.size() != 0) {
				conFollowService.delConFollow(conFollowsDel);
				topContactsService.delTopCon(topContractsDel);
			}
			model.addAttribute("flag","N" );
			model.addAttribute("message", "信息保存成功");
		}
		return "modules/cmag/newcontract/topContracts";
	}
	
	/**
	  * @Description: 检查合同编号是否是唯一的 返回json数据
	  * @param  @param contract
	  * @param  @param contract_no
	  * @param  @param model
	  * @param  @param request
	  * @param  @param redirectAttributes
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月7日
	 */
	@ResponseBody
	@RequestMapping(value = "checkContractNo")
	public FormBean checkContractNo(Contract contract,@RequestParam("contract_no")String contract_no, 
									Model model,HttpServletRequest request,RedirectAttributes redirectAttributes){
		FormBean formBean = new FormBean();
		contract.setContract_no(contract_no);
		long contractCon = contractDataService.findCount(contract);
		if (contractCon >= 1) {
			formBean.setStatus(Result.SUCCESS);
			formBean.setMsg("您填写的合同编号已经存在，请您重新输入！");
		}
		return formBean;
	}
	
	/**
	 * 获取机构 供应商  分销商 JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "companyTreeData")
	public List<Map<String, Object>> companyTreeData() {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		getTreeCool(mapList);
		//组织供应商树形结构
		getTreeSup(mapList);
		//组织分销商树形结构
		getTreeDis(mapList);
		//组织其他信息树形
		getTreeOther(mapList);
		return mapList;
	}
	
	/**
	  * @Description: 查询合同列表 JSON数据。
	  * @param  @return 
	  * @return List<Map<String,Object>>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月30日
	 */
	@ResponseBody
	@RequestMapping(value = "contractTreeData")
	public List<Map<String, Object>>contractTreeData(){
		List<Map<String, Object>> contractList = Lists.newArrayList();
		getContracts(contractList);
		return contractList;
	}
	
	/**
	  * @Description: 区域列表
	  * @param  @param extId  排除的id
	  * @param  @param response
	  * @param  @return 
	  * @return List<Map<String,Object>>  
	  * @throws
	  * @author LJW
	  * @date 2016年6月27日
	 */
	@ResponseBody
	@RequestMapping(value = "areaTreeData")
	public List<Map<String, Object>> areaTreeData(HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Area> list = areaService.findAll();
		for (int i=0; i<list.size(); i++){
			Area e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			if ("0".equals(e.getParentId())) {
				map.put("name", e.getName());
			}else {
				map.put("name", e.getName() + "|" + e.getShort_name());
			}
			mapList.add(map);
		}
		return mapList;
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
	  * @Description: 保存履行跟踪人
	  * @param  @param contract 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月23日
	 */
	private void saveConFollow(Contract contract){
		// 1 insert/update 用设置的履行跟踪人和查询出来的常用联系人比较
		TopContract topContract = new TopContract();
		topContract.setUser_id(UserUtils.getUser().getId());
		String[] lxrs = getTopContactsIdName(topContactsService.findList(topContract));
		String lxzgrIds = contract.getLxgUser().getId();
		String[] lxzgrID = lxzgrIds.split(",");
		List<ConFollow> conFollowsAdd = Lists.newArrayList();
		List<ConFollow> conFollowsUpdate = Lists.newArrayList();
		if (lxrs[0] == null && lxrs[1] == null) {//直接插入  说明没有设置常用联系人
			if ("".equals(lxzgrIds) || lxzgrIds == null) {
				return;
			}
			for (int i = 0; i < lxzgrID.length; i++) {
				ConFollow conFollowAdd = new ConFollow();
				conFollowAdd.setContract_no(contract.getContract_no());
				conFollowAdd.setUser_id(lxzgrID[i]);
				conFollowAdd.setCreateuser_id(UserUtils.getUser().getId());
				conFollowsAdd.add(conFollowAdd);
			}
			conFollowService.saveConFollow(conFollowsAdd);
			return;	
		}
		boolean insertFlag = false;
		boolean updateFlag = false;
		for (int i = 0; i < lxzgrID.length; i++) {
			if (!lxrs[0].contains(lxzgrID[i])) {
				ConFollow conFollowAdd = new ConFollow();
				insertFlag = true;
				conFollowAdd.setContract_no(contract.getContract_no());
				conFollowAdd.setUser_id(lxzgrID[i]);
				conFollowAdd.setCreateuser_id(UserUtils.getUser().getId());
				conFollowsAdd.add(conFollowAdd);
			}else {
				updateFlag = true;
				ConFollow conFollowUpdate = new ConFollow();
				conFollowUpdate.setContract_no(contract.getContract_no());
				conFollowUpdate.setUser_id(lxzgrID[i]);
				conFollowUpdate.setCreateuser_id(UserUtils.getUser().getId());
				conFollowsUpdate.add(conFollowUpdate);
			}
		}
		if (insertFlag) {
			conFollowService.saveConFollow(conFollowsAdd);
		}
		if (updateFlag) {
			conFollowService.saveConFollow(conFollowsUpdate);
			//conFollowService.updateConFollow(conFollowsUpdate);
		}
		// 2 del 用查询出来的常用联系人和设置的履行跟踪人比较
		String[] lxrIds = lxrs[0].split(","); 
		List<ConFollow> conFollowsDel = Lists.newArrayList();
		for (int i = 0; i < lxrIds.length; i++) {
			ConFollow conFollow = new ConFollow();
			if (!lxzgrIds.contains(lxrIds[i])) {
				conFollow.setContract_no(contract.getContract_no());
				conFollow.setUser_id(lxrIds[i]);
				conFollow.setCreateuser_id(UserUtils.getUser().getId());
				conFollowsDel.add(conFollow);
			}
		}
		if (conFollowsDel != null && conFollowsDel.size() != 0) {
			conFollowService.delConFollow(conFollowsDel);
		}
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
		for(int i = 0; i < ids.length; i++){
			ConFollow conFollow = new ConFollow();
			conFollow.setContract_no(contract_no);
			conFollow.setCreateuser_id(UserUtils.getUser().getId());
			conFollow.setUser_id(ids[i]);
			conFollows.add(conFollow);
		}
		return conFollows;
	}
	
	/**
	  * @Description: 遍历查询到的常用联系人把所有的id name 组装成一个字符串
	  * @param  @param topContracts
	  * @param  @return 
	  * @return String[]  
	  * @throws
	  * @author LJW
	  * @date 2016年5月20日
	 */
	private String[] getTopContactsIdName(List<TopContract> topContracts){
		String[] tmStrings = new String[2];
    	StringBuffer ids = new StringBuffer();
    	StringBuffer names = new StringBuffer();
    	if(topContracts != null && topContracts.size() != 0){
    		for (TopContract topContract : topContracts) {
        		ids.append(topContract.getSecond_id()).append(",");
        		names.append(systemService.getUser(topContract.getSecond_id()).getName()).append(",");
    		}
    		ids = ids.deleteCharAt(ids.length() - 1);
        	names = names.deleteCharAt(names.length() - 1);
        	tmStrings[0] = ids.toString();
        	tmStrings[1] = names.toString();
    	}
    	
		return tmStrings;
	}
	
	/**
	  * @Description: 获取合同列表
	  * @param  @param contractList 
	 * @return 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月30日
	 */
	private List<Map<String, Object>> getContracts(List<Map<String, Object>> contractList) {
		Map<String, Object> tmpContract = Maps.newHashMap();
		tmpContract.put("id", "1");
		tmpContract.put("pId", "");
		tmpContract.put("pIds", "0,");
		tmpContract.put("name", "关联合同列表");
		contractList.add(tmpContract);
		List<Contract> contracts = contractDataService.findContracts();
		for (int i = 0; i < contracts.size(); i++) {
			Contract contract = contracts.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", contract.getContract_no());
			map.put("pId", "1");
			map.put("pIds", "1");
			map.put("name", contract.getContract_no_name()+"|"+contract.getContract_name());
			contractList.add(map);
		}
		return contractList;
	}

	/**
	  * @Description: 获取酷秀集团列表
	  * @param  @param mapCool
	  * @param  @return 
	  * @return List<Map<String,Object>>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月29日
	 */
	private List<Map<String, Object>> getTreeCool(List<Map<String, Object>> mapCool){
		Map<String, Object> tmpCool = Maps.newHashMap();
		tmpCool.put("id", "1");
		tmpCool.put("pId", "");
		tmpCool.put("pIds", "0,");
		tmpCool.put("name", "酷秀集团");
		mapCool.add(tmpCool);
		List<ListTool> cTools = DmTypeUtils.getOfficeTypeList();
		for (int i = 0; i < cTools.size(); i++) {
			ListTool listTool = cTools.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", listTool.getId()+"|0");
			map.put("pId", "1");
			map.put("pIds", "1");
			map.put("name", listTool.getName());
			mapCool.add(map);
		}
		return mapCool;
	}
	
	/**
	  * @Description: 获取分销商树列表
	  * @param  @return 
	  * @return List<Map<String,Object>>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月27日
	 */
	private List<Map<String, Object>> getTreeSup(List<Map<String, Object>> mapDis){
		Map<String, Object> tmpDis = Maps.newHashMap();
		tmpDis.put("id", "200000");
		tmpDis.put("pId", "");
		tmpDis.put("pIds", "0,");
		tmpDis.put("name", "分销商列表");
		mapDis.add(tmpDis);
		List<DistributorBase> dList = distributorBaseService.findList();  
		for (int i = 0; i < dList.size(); i++) {
			DistributorBase distributorBase = dList.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", distributorBase.getId()+"|1");
			map.put("pId", "200000");
			map.put("pIds", "200000");
			map.put("name", distributorBase.getName());
			mapDis.add(map);
		}
		return mapDis;
	}
	
	/**
	  * @Description: 获取供应商树列表
	  * @param  @return 
	  * @return List<Map<String,Object>>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月27日
	 */
	private List<Map<String, Object>> getTreeDis(List<Map<String, Object>> maps){
		Map<String, Object> tmpSup = Maps.newHashMap();
		//组织供应商树形结构
		tmpSup.put("id", "100000");
		tmpSup.put("pId", "");
		tmpSup.put("pIds", "0,");
		tmpSup.put("name", "供应商列表");
		maps.add(tmpSup);
		List<SupplierBase> sList = supplierBaseService.findList();  
		for (int i = 0; i < sList.size(); i++) {
			SupplierBase suBase = sList.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", suBase.getId()+"|2");
			map.put("pId", "100000");
			map.put("pIds", "100000");
			map.put("name", suBase.getName());
			maps.add(map);
		}
		return maps;
	}
	
	/**
	  * @Description: 获取其他信息树型列表
	  * @param  @param mapOthers
	  * @param  @return 
	  * @return List<Map<String,Object>>  
	  * @throws
	  * @author LJW
	  * @date 2016年6月6日
	 */
	private List<Map<String, Object>> getTreeOther(List<Map<String, Object>>mapOthers){
		Map<String, Object> tmpOther = Maps.newHashMap();
		//组织其他信息列表
		tmpOther.put("id", "300000");
		tmpOther.put("pId", "");
		tmpOther.put("pIds", "0,");
		tmpOther.put("name", "其他信息列表");
		mapOthers.add(tmpOther);
		List<Other> others = otherService.findList();
		for (int i = 0; i < others.size(); i++) {
			Other other = others.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", other.getCode());
			map.put("pId","300000");
			map.put("pIds","300000");
			map.put("name", other.getName());
			mapOthers.add(map);
		}
		return mapOthers;
	}
	
	/**
	 * @Description: 拆分前台传递过来的常用联系人信息，并封装成List
	 * @author WQH
	 * @date 2016年5月17日
	 */
	private List<TopContract> getTopContract(String user_id,String second_id){
		List<TopContract> topContracts = Lists.newArrayList();
		String[] second_ids = second_id.split(",");
		for (int i = 0; i < second_ids.length; i++) {
			TopContract topContract = new TopContract();
			topContract.setUser_id(user_id);
			topContract.setSecond_id(second_ids[i]);
			topContracts.add(topContract);
		}
		return topContracts;
	}
	
	
	/**
	 * @Description: 拆分设置合同常用跟踪人界面传递过来的常用联系人信息,并组装成List
	 * @author WQH
	 * @date 2016年5月18日
	 */
	private List<ConFollow> getConFollows(String user_id,String second_id){
		List<ConFollow> conFollows = Lists.newArrayList();
		String[] sids = second_id.split(",");
		for(int i = 0; i < sids.length; i++){
			ConFollow conFollow = new ConFollow();
			conFollow.setCreateuser_id(user_id);
			conFollow.setContract_no("");
			conFollow.setUser_id(sids[i]);
			conFollows.add(conFollow);
		}
		return conFollows;
	}
	
	/**
	  * @Description:判断合同的日期类型的数据是否是""，是的强制设置为null，防止抛出异常 
	  * @param  @param contract
	  * @param  @return 
	  * @return Contract  
	  * @throws
	  * @author LJW
	  * @date 2016年7月27日
	 */
	private Contract setDateTypeByNULL(Contract contract){
		if (StringUtils.isBlank(contract.getQd_date())){
			contract.setQd_date(null);
		}
		if (StringUtils.isBlank(contract.getBegin_date())){
			contract.setBegin_date(null);
		}
		if (StringUtils.isBlank(contract.getEnd_date())){
			contract.setEnd_date(null);
		}
		return contract;
	}
}