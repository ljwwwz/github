package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.persistence.Page;
import com.coolshow.frm.jeesite.common.service.TreeService;
import com.coolshow.jeesite.common.tool.DateTool;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.ISupplierBaseDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Supplier;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.SupplierBase;


/**
 * @Description 供应商Service
 * ClassName: SupplierBaseService.java
 * @author LJW
 * @date 上午8:35:41
 */
@Service
@Transactional(readOnly = true)
public class SupplierBaseService extends TreeService<ISupplierBaseDao, SupplierBase> {

	@Autowired
	private ISupplierBaseDao iSupplierBaseDao;
	
	/**
	  * @Description: 查询供应商列表
	  * @param  @return 
	  * @return List<Supplier>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月26日
	 */
	@Transactional(readOnly = true)
	public List<SupplierBase> findList(){
		return iSupplierBaseDao.findList();
	}
	
	/**
	  * @Description: 分页查询供应商基本信息
	  * @param  @param page
	  * @param  @param supplierBase
	  * @param  @return 
	  * @return Page<SupplierBase>  
	  * @throws
	  * @author LJW
	  * @date 2016年6月14日
	 */
	@Transactional(readOnly = true)
	public Page<SupplierBase> findPages(Page<SupplierBase> page, SupplierBase supplierBase) {
		supplierBase.setPage(page);
		page.setList(iSupplierBaseDao.findList(supplierBase));
		return page;
	}

	/**
	 * @Description:查询后7位最大客商编码
	 * @return String
	 * @author WQH
	 * @date 2016年6月14日
	 */
	@Transactional(readOnly = true)
	public String findMax() {
		return iSupplierBaseDao.findMax();
	}

	/**
	 * @Description:新增供应商客商信息
	 * @param supplierBase
	 * @author WQH
	 * @date 2016年6月14日
	 */
	@Transactional(readOnly = false)
	public void saveInfo(SupplierBase supplierBase) {
		supplierBase.setIsNewRecord(true);
		supplierBase.preInsert();
		supplierBase.setCreate_user(supplierBase.getCreateBy().getName());
		supplierBase.setCreate_time(DateTool.uf_DateTimeToLongString(supplierBase.getCreateDate()));
		iSupplierBaseDao.saveInfo(supplierBase);
	}
	
	/**
	  * @Description: 更新供应商基本信息 
	  * @param  @param supplierBase 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年6月14日
	 */
	@Transactional(readOnly = false)
	public void updateSupBase(SupplierBase supplierBase){
		supplierBase.preUpdate();
		supplierBase.setModify_user(supplierBase.getUpdateBy().getName());
		supplierBase.setModify_time(DateTool.uf_DateTimeToLongString(supplierBase.getUpdateDate()));
		iSupplierBaseDao.updateSupBase(supplierBase);
	}
	
	/**
	  * @Description: 逻辑删除  del_flag=1
	  * @param  @param supplierBase 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年6月15日
	 */
	@Transactional(readOnly = false)
	public int delSupBase(SupplierBase supplierBase){
		supplierBase.preUpdate();
		supplierBase.setModify_user(supplierBase.getUpdateBy().getName());
		supplierBase.setModify_time(DateTool.uf_DateTimeToLongString(supplierBase.getUpdateDate()));
		return iSupplierBaseDao.delSupBase(supplierBase);
	}
	
	/**
	  * @Description: 查询单条记录
	  * @param  @param supplierBase
	  * @param  @return 
	  * @return SupplierBase  
	  * @throws
	  * @author LJW
	  * @date 2016年6月16日
	 */
	public SupplierBase findSupplierBase(SupplierBase supplierBase){
		return iSupplierBaseDao.get(supplierBase);
	}
}
