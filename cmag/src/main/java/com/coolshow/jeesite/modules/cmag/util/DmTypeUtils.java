package com.coolshow.jeesite.modules.cmag.util;

import java.util.List;

import com.coolshow.frm.jeesite.common.utils.SpringContextHolder;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ListTool;
import com.coolshow.jeesite.modules.cmag.util.dao.IDmTypeUtilsDAO;
import com.google.common.collect.Lists;

/**
 * @Description:获取用户/公司列表工具类
 * ClassName: DmTypeUtils
 * @author LJW
 * @date 2016年5月9日
 */
public class DmTypeUtils{

	private static IDmTypeUtilsDAO iTypeUtilsDAO = SpringContextHolder.getBean(IDmTypeUtilsDAO.class);
	
	/**
	 * @Description: 根据用户类型 查询用户列表
	 * @param @param type
	 * @param @return   
	 * @return List<TopContacts>  
	 * @throws
	 * @author LJW
	 * @date 2016年5月9日
	 */
	public static List<ListTool> getUserTypeList(String type){
		List<ListTool> topContacts = iTypeUtilsDAO.findByTypeList(type);
		if (topContacts == null){
			topContacts = Lists.newArrayList();
		}
		return topContacts;
	}
	
	/**
	  * @Description: 根据公司类型查询公司列表
	  * @param  @param type
	  * @param  @return 
	  * @return List<ListTool>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月29日
	 */
	public static List<ListTool> getOfficeTypeList(){
		List<ListTool> officeLists = iTypeUtilsDAO.findByTypeOfficeList();
		if (officeLists == null) {
			officeLists = Lists.newArrayList();
		}
		return officeLists;
	}
	
}
