/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.coolshow.jeesite.modules.cmag.kswh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coolshow.frm.jeesite.common.beanvalidator.BeanValidators;
import com.coolshow.frm.jeesite.common.config.Global;
import com.coolshow.frm.jeesite.common.persistence.Page;
import com.coolshow.frm.jeesite.common.web.BaseController;
import com.coolshow.frm.jeesite.common.utils.DateUtils;
import com.coolshow.frm.jeesite.common.utils.IdGen;
import com.coolshow.frm.jeesite.common.utils.StringUtils;
import com.coolshow.frm.jeesite.common.utils.excel.ExportExcel;
import com.coolshow.frm.jeesite.common.utils.excel.ImportExcel;
import com.coolshow.frm.jeesite.modules.sys.entity.Office;
import com.coolshow.frm.jeesite.modules.sys.entity.User;
import com.coolshow.frm.jeesite.modules.sys.service.SystemService;
import com.coolshow.frm.jeesite.modules.sys.utils.UserUtils;
import com.coolshow.jeesite.common.datagrid.DataRequest;
import com.coolshow.jeesite.common.datagrid.DataResponse;
import com.coolshow.jeesite.common.tool.ContractStateEnum;
import com.coolshow.jeesite.common.tool.ContractTypeEnum;
import com.coolshow.jeesite.common.tool.DateTool;
import com.coolshow.jeesite.common.tool.FileTool;
import com.coolshow.jeesite.common.tool.FormBean;
import com.coolshow.jeesite.common.tool.UuidFactory;
import com.coolshow.jeesite.common.tool.FormBean.Result;
import com.coolshow.jeesite.modules.cmag.conmanger.service.ConManagerDataService;
import com.coolshow.jeesite.modules.cmag.kswh.service.CustomManagerService;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ConFollow;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ContractType;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Distributor;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.DistributorBase;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Enclosure;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Other;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.RelationContract;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Scanning;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Supplier;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.SupplierBase;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.VerifyInfo;
import com.coolshow.jeesite.modules.cmag.newcontract.service.ConFollowService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.ContractDataService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.DistributorBaseService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.DistributorService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.EnclosureService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.ExtendSystemService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.OtherService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.RelationContractService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.ScanningService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.SupplierBaseService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.SupplierService;
import com.coolshow.jeesite.modules.cmag.newcontract.service.VerifyInfoService;
import com.google.common.collect.Lists;

/**
 * @Description 合同客商信息管理
 * ClassName: CustomMangerController
 * @author LJW
 * @param <T>
 * @date 2016年5月10日
 */
@Controller
@RequestMapping(value = "${adminPath}/cmag/contract/ks")
public class CustomMangerController extends BaseController {

	@Autowired
	private ConManagerDataService conManagerDataService;
	@Autowired
	private ContractDataService contractDataService;
	@Autowired
	private SupplierBaseService supplierBaseService;
	@Autowired
	private DistributorBaseService distributorBaseService;
	@Autowired
	private OtherService otherService;
	@Autowired
	private CustomManagerService customManagerService;
	
	/**
	  * @Description: 显示查询列表页面
	  * @param  @param contract
	  * @param  @param request
	  * @param  @param response
	  * @param  @param model
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author WQH
	  * @date 2016年6月21日
	 */
	@RequestMapping(value="showMainPage")
	public String showMainPage(Contract contract, HttpServletRequest request, HttpServletResponse response, Model model){
		return "modules/cmag/ksgl/KsQueryMainPage";
	}
	
	/**
	  * @Description: 初始化列表页面
	  * @param  @param contract
	  * @param  @param request
	  * @param  @param response
	  * @param  @param model
	  * @param  @param redirectAttributes
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月23日
	 */
	@RequiresPermissions("cmag:contract:ks:view")
	@RequestMapping(value = {"list", ""})
	public String list(Contract contract, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		try {
			if(Global.isDemoMode()){
				addMessage(redirectAttributes, "演示模式，不允许操作！");
				return "redirect:" + adminPath + "/cmag/contract/ks/list";
			}
		}catch (Exception e) {
			model.addAttribute("message",e.getMessage());
		}
		return "modules/cmag/ksgl/KsQueryPage";
	}
	
	/**
	  * @Description: 客商信息展示
	  * @param  @param testData
	  * @param  @param request
	  * @param  @param response
	  * @param  @param model
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月14日
	 */
	@RequiresPermissions("cmag:contract:ks:view")
	@RequestMapping(value = "show")
	public String show(Contract contract,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(Global.isDemoMode()){
			model.addAttribute("message", "演示模式，不允许操作！");
			return "modules/cmag/ksgl/addKsPage";
		}
		String flag = request.getParameter("flag");
		if ("00".equals(flag)) {
			String contractTypeDm = request.getParameter("contractTypeDm");
			String id =request.getParameter("id");
			String name = "";
			if(ContractTypeEnum.SUPPLIER.getIndex().equals(contractTypeDm)){
				SupplierBase supplierBase = new SupplierBase();
				supplierBase.setId(id);
				supplierBase = supplierBaseService.findSupplierBase(supplierBase);
				if (supplierBase != null) {
					name = supplierBase.getName();
				}
			}
			if(ContractTypeEnum.DISTRIBUTOR.getIndex().equals(contractTypeDm)){
				DistributorBase distributorBase = new DistributorBase();
				distributorBase.setId(id);
				distributorBase = distributorBaseService.finDistributorBase(distributorBase);
				if (distributorBase != null) {
					name = distributorBase.getName();
				}
			}
			if(ContractTypeEnum.QT.getIndex().equals(contractTypeDm)){
				Other other = new Other();
				other.setCode(id);
				other = otherService.finOther(other);
				if (other != null) {
					name = other.getName();
				}
			}
			ContractType contractType = new ContractType();
			contractType.setTypeDm(contractTypeDm);
			contract.setContractType(contractType);
			model.addAttribute("name",name);
			model.addAttribute("contract", contract);
			model.addAttribute("updateFlag","Y");
			model.addAttribute("id", id);
		}
		return "modules/cmag/ksgl/addKsPage";
	}


	@ResponseBody  
	@RequestMapping(value="list1")
	@SuppressWarnings("rawtypes")
    public DataResponse list1(@RequestParam(defaultValue="1",value="page") String page, @RequestParam(defaultValue="10",value="rows") String rows,  
            Model model,HttpServletRequest httpServletRequest){  
			String contractTypeDm = "";
			String name = "";
        try {  
        	name = httpServletRequest.getParameter("ksName");
        	contractTypeDm = httpServletRequest.getParameter("contractTypeDm");
            DataRequest request = new DataRequest();  
            request.setPage(StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page));  
            request.setRows(StringUtils.isEmpty(rows) ? 10 : Integer.valueOf(rows));  
			if(ContractTypeEnum.SUPPLIER.getIndex().equals(contractTypeDm)){
				SupplierBase supplierBase = new SupplierBase();
				supplierBase.setName(name);
				return customManagerService.searchSup(request,supplierBase); 
			}
			if(ContractTypeEnum.DISTRIBUTOR.getIndex().equals(contractTypeDm)){
				DistributorBase distributorBase = new DistributorBase();
				distributorBase.setName(name);
				return customManagerService.searchDis(request, distributorBase);
			}
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }
	
	/**
	 * @Description：新增
	 * @param contract
	 * @param request
	 * @param response
	 * @param model
	 * @return String
	 * @throws
	 * @author WQH
	 * @Date 2016年6月14日
	 */
	@RequiresPermissions("cmag:contract:ks:view")
	@RequestMapping(value = "saveKsInfo")	
	public String saveKsInfo(Contract contract,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		String contractTypeDm = "";
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/cmag/contract/ks/list";
		}
		if(contract.getContractType() != null){
			contractTypeDm = contract.getContractType().getTypeDm();
			model.addAttribute("contractTypeDm", contractTypeDm);
		}
		if(ContractTypeEnum.SUPPLIER.getIndex().equals(contractTypeDm)){
			SupplierBase supplierBase = new SupplierBase();
			String number = supplierBaseService.findMax();
			String ksNumber = getChangeAdd(number);
			supplierBase.setSupplier_no(ksNumber);
			supplierBase.setName(request.getParameter("ksName"));
			supplierBase.setDel_flag("0");
			supplierBaseService.saveInfo(supplierBase);
		}
		if(ContractTypeEnum.DISTRIBUTOR.getIndex().equals(contractTypeDm)){
			DistributorBase distributorBase = new DistributorBase();
			String number = distributorBaseService.findMax();
			String ksNumber = getChangeAdd(number);
			distributorBase.setDistributor_no(ksNumber);
			distributorBase.setName(request.getParameter("ksName"));
			distributorBase.setDel_flag("0");
			distributorBaseService.saveInfo(distributorBase);
		}
		model.addAttribute("message","信息保存成功");
		return "modules/cmag/ksgl/addKsPage";
	}
	
	/**
	  * @Description: 逻辑删除客商数据
	  * @param  @param contract
	  * @param  @param id
	  * @param  @param request
	  * @param  @param response
	  * @param  @param model
	  * @param  @param redirectAttributes
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月14日
	 */
	@ResponseBody
	@RequestMapping(value="delete")
	public FormBean delete(@RequestParam("id")String id,@RequestParam("contractTypeDm")String contractTypeDm, HttpServletRequest request, HttpServletResponse response, Model model){
		FormBean formBean = new FormBean();
		try {
			int delCount = 0;
			if(ContractTypeEnum.SUPPLIER.getIndex().equals(contractTypeDm)){
				SupplierBase supplierBase = new SupplierBase();
				supplierBase.setId(id);
				supplierBase.setDel_flag("1");
				delCount = supplierBaseService.delSupBase(supplierBase);
			}
			if(ContractTypeEnum.DISTRIBUTOR.getIndex().equals(contractTypeDm)){
				DistributorBase distributorBase = new DistributorBase();
				distributorBase.setId(id);
				distributorBase.setDel_flag("1");
				delCount = distributorBaseService.delDisBase(distributorBase);
			}
			if (delCount >= 1) {
				formBean.setStatus(Result.SUCCESS);
				formBean.setMsg("删除合同主体信息成功！");
			}
			return formBean;
		} catch (Exception e) {
			formBean.setStatus(Result.FAILURE);
			formBean.setMsg("删除合同主体信息失败：原因是【"+e.getMessage()+"】");
		}
		return null;
	}
	
	/**
	  * @Description: 修改客商数据
	  * @param  @param contract
	  * @param  @param id
	  * @param  @param request
	  * @param  @param response
	  * @param  @param model
	  * @param  @param redirectAttributes
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月14日
	 */
	@RequestMapping(value="update")
	public String update(Contract contract,HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes){
		String contractTypeDm = "";
		String name = "";
		try {
			if(Global.isDemoMode()){
				addMessage(redirectAttributes, "演示模式，不允许操作！");
				return "redirect:" + adminPath + "/cmag/contract/ks/list?repage";
			}
			if(contract.getContractType() != null){
				contractTypeDm = contract.getContractType().getTypeDm();
				model.addAttribute("contractTypeDm", contractTypeDm);
			}
			name = request.getParameter("ksName");
			String id = request.getParameter("idValue");
			if(ContractTypeEnum.SUPPLIER.getIndex().equals(contractTypeDm)){
				SupplierBase supplierBase = new SupplierBase();
				supplierBase.setId(id);
				supplierBase.setName(name);
				supplierBaseService.updateSupBase(supplierBase);
			}
			if(ContractTypeEnum.DISTRIBUTOR.getIndex().equals(contractTypeDm)){
				DistributorBase distributorBase = new DistributorBase();
				distributorBase.setId(id);
				distributorBase.setName(name);
				distributorBaseService.updateDisBase(distributorBase);
			}
			addMessage(redirectAttributes, "修改客商数据成功！");
		} catch (Exception e) {
			addMessage(redirectAttributes, "修改客商数据出现异常！");
		}
		
		return "redirect:" + adminPath + "/cmag/contract/ks/list?contractTypeDm="+contractTypeDm+"&repage";
	}
	
	/**
	 * @Description:客商编号类型转换自增1
	 * @param number
	 * @return
	 */
	private String getChangeAdd(String number) {
		double temp = Double.valueOf(number)+1;
		String no = "";
		if(number.length() < 9){
			 no ="KS20"+String.valueOf(temp);
		}
		else{
			 no ="KS"+String.valueOf(temp);
		}	
		return no;
	}
}