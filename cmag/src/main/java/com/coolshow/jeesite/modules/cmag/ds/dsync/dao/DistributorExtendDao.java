package com.coolshow.jeesite.modules.cmag.ds.dsync.dao;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.ds.dsync.entity.SimpleDistributor;

/**
 * 分销商基本信息表
 * @author ay_lin
 *
 */
@MyBatisDao
public interface DistributorExtendDao extends CrudDao<SimpleDistributor>{
	
	/**
	 * 根据客商编码查询客商
	 * @param ksCode 客商编码
	 * @return
	 */
	public SimpleDistributor get(String ksCode);

	
	/**
	 * 根据客商名称查询客商
	 * @param ksName 客商名称
	 * @return
	 */
	public String findCmagDcByName(String ksName);

	
	/**
	 * 插入一条分销商信息
	 * @param cmagDc
	 * @return
	 */
	public int insert(SimpleDistributor distributor);
	
	
	
	/**
	 * 更新分销商信息
	 * @param cmagDc
	 * @return
	 */
	public int update(SimpleDistributor distributor);

	
	/**
	 * 删除分销商数据
	 * @param cmagDc
	 * @return
	 */
	public int delete(SimpleDistributor distributor);
	
	
	
	

	
}
