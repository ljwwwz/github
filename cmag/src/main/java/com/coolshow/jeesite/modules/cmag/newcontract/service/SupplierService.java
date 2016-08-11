package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.frm.jeesite.common.utils.SpringContextHolder;
import com.coolshow.jeesite.common.tool.DateTool;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.ISupplierDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Supplier;
import com.coolshow.jeesite.modules.cmag.util.dao.IDmTypeUtilsDAO;

/**
 * @Description 供应商数业务操作类
 * ClassName: SupplierService.java
 * @author LJW
 * @date 下午4:50:57
 */
@Service
@Transactional(readOnly = true)
public class SupplierService extends CrudService<ISupplierDao, Supplier>{

	@Autowired
	private ISupplierDao iSupplierDao;
	
	/**
	  * @Description: 保存供应商合同信息
	  * @param  @param supplier 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = false)
	public void save(Supplier supplier){
		supplier.setIsNewRecord(true);
		supplier.preInsert();
		iSupplierDao.insert(supplier);
	}
	
	/**
	  * @Description: 根据合同编号查询合同信息
	  * @param  @param contract_no
	  * @param  @return 
	  * @return Supplier  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = true)
	public Supplier findSupplier(String contract_no){
		return iSupplierDao.findSupplier(contract_no);
	}
	
	/**
	  * @Description: 根据合同编号更新供应商合同信息 
	  * @param  @param supplier 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = false)
	public void updateSupplier(Supplier supplier){
		supplier.preUpdate();
		supplier.setModify_user(supplier.getUpdateBy().getName());
		supplier.setModify_time(DateTool.uf_DateTimeToLongString(supplier.getUpdateDate()));
		iSupplierDao.updateSupplier(supplier);
	}
	
	
	/**
	  * @Description: 批量插入
	  * @param  @param suppliers 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年6月1日
	 */
	public void insertList(List<Supplier> suppliers){
		iSupplierDao.insertList(suppliers);
	}
}
