package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Supplier;
/**
 * @Description 供应商数据库操作类
 * ClassName: ISupplierDao
 * @author LJW
 * @date 2016年5月10日
 */
@MyBatisDao
public interface ISupplierDao extends CrudDao<Supplier>{

	public Supplier findSupplier(String contract_no);
	
	/**
	  * @Description: 根据合同编号更新供应商合同信息
	  * @param  @param supplier
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	public int updateSupplier(Supplier supplier);
	
	/**
	  * @Description: 批量插入
	  * @param  @param suppliers
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年6月1日
	 */
	public int insertList(List<Supplier> suppliers);
}
