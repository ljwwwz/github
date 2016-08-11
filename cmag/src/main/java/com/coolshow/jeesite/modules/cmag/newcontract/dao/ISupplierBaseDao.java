package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coolshow.frm.jeesite.common.persistence.TreeDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.SupplierBase;
import com.coolshow.jeesite.modules.cmag.newcontract.service.SupplierBaseService;

/**
 * @Description 供应商Dao
 * ClassName: ISupplierBaseDao.java
 * @author LJW
 * @date 上午8:21:53
 */
@MyBatisDao
public interface ISupplierBaseDao extends TreeDao<SupplierBase>{

	/**
	  * @Description: 查询供应商列表
	  * @param  @return 
	  * @return List<SupplierBase>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月27日
	 */
	public List<SupplierBase> findList();
	
	/**
	  * @Description: 分页查询
	  * @param  @param start
	  * @param  @param limit
	  * @param  @return 
	  * @return List<SupplierBase>  
	  * @throws
	  * @author LJW
	  * @date 2016年6月22日
	 */
	public List<SupplierBase>findListLimit(@Param("supplierBase")SupplierBase supplierBase);
	//public List<SupplierBase>findListLimit(@Param("supplierBase")SupplierBase supplierBase,@Param(value = "start")int start,@Param(value = "limit")int limit);
	
	/**
	 * @Description:查询后7位最大客商编码
	 * @return String
	 * @author WQH
	 * @date 2016年6月14日
	 */
	public String findMax();

	/**
	 * @Description:新增供应商客商信息
	 * @param supplierBase
	 * @author WQH
	 * @date 2016年6月14日
	 */
	public void saveInfo(SupplierBase supplierBase);
	
	/**
	  * @Description: 更新供应商基本信息 
	  * @param  @param supplierBase
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年6月14日
	 */
	public int updateSupBase(SupplierBase supplierBase);
	
	/**
	  * @Description: 逻辑删除  del_flag=1
	  * @param  @param supplierBase
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年6月15日
	 */
	public int delSupBase(SupplierBase supplierBase);
	
	/**
	 * 查询单条记录
	 */
	public SupplierBase get(SupplierBase supplierBase);
}
