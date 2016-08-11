/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.coolshow.jeesite.modules.cmag.newcontract.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coolshow.frm.jeesite.common.config.Global;
import com.coolshow.frm.jeesite.common.utils.StringUtils;
import com.coolshow.frm.jeesite.common.web.BaseController;
import com.coolshow.frm.jeesite.modules.sys.entity.User;
import com.coolshow.frm.jeesite.modules.sys.service.SystemService;
import com.coolshow.frm.jeesite.modules.sys.utils.UserUtils;
import com.coolshow.jeesite.modules.cmag.newcontract.service.ExtendSystemService;
/**
 * 用户Controller
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/extend/sys/user")
public class ExtendUserController extends BaseController {

	@Autowired
	private ExtendSystemService systemService;
	
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getUser(id);
		}else{
			return new User();
		}
	}
	
	/**
	 * 用户信息显示及保存
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(User user, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/extendUserInfo";
			}
		}
		//统计当前用户的合同生效数量
//		long fulInfo = systemService.getFulfillInfo(currentUser.getId());
		long fulInfo = systemService.getFulfillInfo(currentUser);
		//统计当前用户的未上传扫描件合同数量
		long upScanInfo = systemService.getUpScanInfo(currentUser);
		//统计当前用户的待发送初审合同数量
//		long preSend = systemService.getPreSend(currentUser.getId());
		long preSend = systemService.getPreSend(currentUser);
		//统计当前用户的初审中合同数量
		long verifying = systemService.getVerifying(currentUser);
		//统计当前用户的待确认扫描件数量
		long scanConfirm = systemService.getScanConfirm(currentUser);
		model.addAttribute("scanConfirm", scanConfirm);
		model.addAttribute("verifying", verifying);
		model.addAttribute("preSend", preSend);
		model.addAttribute("upScanInfo", upScanInfo);
		model.addAttribute("fulInfo", fulInfo);
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/extendUserInfo";
	}
	
}
