package com.coolshow.jeesite.modules.cmag.util.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ListTool;

/**
 * @Description:获取列表数据
 * ClassName: IDmTypeUtilsDAO
 * @author LJW
 * @date 2016年5月9日
 */
@MyBatisDao
public interface IDmTypeUtilsDAO extends CrudDao<ListTool> {

	/**
	 * @Description: 根据用户类型查询用户列表
	 * @param @param userType
	 * @param @return   
	 * @return List<ListTool>  
	 * @throws
	 * @author LJW
	 * @date 2016年5月9日
	 */
	public List<ListTool> findByTypeList(String userType);
	
	/**
	 * @Description: 根据类型查询公司列表
	 * @param @param type
	 * @param @return   
	 * @return List<ListTool>  
	 * @throws
	 * @author LJW
	 * @date 2016年5月9日
	 */
	public List<ListTool> findByTypeOfficeList();
}
