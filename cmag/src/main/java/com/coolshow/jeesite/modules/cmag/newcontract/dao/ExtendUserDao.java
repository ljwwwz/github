/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.frm.jeesite.modules.sys.entity.User;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface ExtendUserDao extends CrudDao<User> {
	
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	

	/**
	 * 查询生效中的合同数量
	 * @author WQH
	 * @return
	 */
	public long getFulfillInfo(@Param(value="id")String id);

	/**
	 * 查询未上传扫描件的合同数量
	 * @author WQH
	 * @return
	 */
	public long getUpScanInfo(@Param(value="id")String id);
	
	/**
	 * 查询待发送初审的合同数量
	 * @author WQH
	 * @return
	 */
	public long getPreSend(@Param(value="id")String id);

	/**
	 * 查询初审中的合同数量
	 * @author WQH
	 * @return
	 */
	public long getVerifying(@Param(value="id")String id);

	/**
	 * 查询上传附件件的合同数量
	 * @author WQH
	 * @return
	 */
	public long getEnclInfo(String id);

	/**
	 * 查询未上传扫描件的合同信息
	 * @param contract_no
	 * @return
	 */
	public long findNotUpScan(String contract_no);

	/**
	 * 查询扫描件待确认的合同数量
	 * @author WQH
	 * @return
	 */
	public long getScanConfirm(@Param(value="id")String id);
	
}
