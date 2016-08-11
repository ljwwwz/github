/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.coolshow.jeesite.modules.cmag.newcontract.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.service.BaseService;
import com.coolshow.frm.jeesite.modules.sys.entity.User;
import com.coolshow.frm.jeesite.modules.sys.utils.UserUtils;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.ExtendUserDao;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author ThinkGem
 * @version 2013-12-05
 */
@Service
@Transactional(readOnly = true)
public class ExtendSystemService extends BaseService implements InitializingBean {
	
	@Autowired
	private  ExtendUserDao userDao;
	
	@Transactional(readOnly = false)
	public void updateUserInfo(User user) {
		user.preUpdate();
		userDao.updateUserInfo(user);
		// 清除用户缓存
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	/**
	 * 获取用户
	 * @param id
	 * @return
	 */
	public User getUser(String id) {
		return UserUtils.get(id);
	}

	@Override
	public void afterPropertiesSet() throws Exception { }

	/**
	 * 获取未上传扫描件的合同信息
	 * @param contract_no
	 * @return
	 */
	public  long findNotUpScan(String contract_no) {
		long counts = userDao.findNotUpScan(contract_no);
		return counts;
	}
	
	//查询扫描件待确认的合同数量
	public long getScanConfirm(User user) {
		String id = getId(user);
		long counts = userDao.getScanConfirm(id);
		return counts;
	}
	
	//查询待发送初审的合同数量
	public long getPreSend(User user) {
		String id = getId(user);
		long counts = userDao.getPreSend(id);
		return counts;
	}
	
	//查询初审中的合同数量
	public long getVerifying(User user) {
		String id = getId(user);
		long counts = userDao.getVerifying(id);
		return counts;
	}
	
	//查询生效中的合同数量
	public long getFulfillInfo(User user) {
		String id = getId(user);
		long counts = userDao.getFulfillInfo(id);
		return counts;
	}

	//查询未上传扫描件的合同数量
	public long getUpScanInfo(User user) {
		String id = getId(user);
		long counts = userDao.getUpScanInfo(id);
		return counts;
	}

	//查询上传附件的合同数量
	public long getEnclInfo(String id) {
		long counts = userDao.getEnclInfo(id);
		return counts;
	}

	/**
	  * @Description: 判断当前登录人是否是管理员 
	  * 				 任务分配和超级管理员角色，并获取id
	  * @param  @param user
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月17日
	 */
	@SuppressWarnings("unused")
	private String getId(User user){
		String curUserRoleType = UserUtils.getRoleList().get(0).getRoleType();
		String curUserId = UserUtils.getUser().getId();
		String id = "";
		if("1".equals(curUserId) || "assignment".equals(curUserRoleType) || "security-role".equals(curUserRoleType)){
			id = "admin";
		}else {
			id = user.getId();
		}
		return id;
	}


}
