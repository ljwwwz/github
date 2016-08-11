package com.coolshow.jeesite.modules.cmag.ds.dsync.dao;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.ds.dsync.entity.SimpleSupplier;

/**
 * 供应商基本信息表
 * @author ay_lin
 *
 */
@MyBatisDao
public interface SupplierExtendDao extends CrudDao<SimpleSupplier>{
	
	/**
	 * 根据供应商编码查询供应商
	 * @param ksCode
	 * @return
	 */
	public SimpleSupplier get(String ksCode);

	
	/**
	 * 根据供应商名称查询供应商
	 * @param ksName
	 * @return
	 */
	public String findCmagDcByName(String ksName);

	
	/**
	 * 插入一条供应商信息
	 * @param cmagDc
	 * @return
	 */
	public int insert(SimpleSupplier supplier);
	
	
	
	/**
	 * 更新供应商信息
	 * @param cmagDc
	 * @return
	 */
	public int update(SimpleSupplier supplier);

	
	/**
	 * 删除供应商数据
	 * @param cmagDc
	 * @return
	 */
	public int delete(SimpleSupplier supplier);
	
	
	
	

	
}
