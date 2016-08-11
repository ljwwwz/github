package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.persistence.Page;
import com.coolshow.frm.jeesite.common.service.TreeService;
import com.coolshow.jeesite.common.tool.DateTool;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IDistributorBaseDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Distributor;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.DistributorBase;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.SupplierBase;

/**
 * @Description 分销商Service
 * ClassName: DistributorBaseService.java
 * @author LJW
 * @date 上午8:37:47
 */
@Service
@Transactional(readOnly = true)
public class DistributorBaseService extends TreeService<IDistributorBaseDao, DistributorBase> {

	@Autowired
	private IDistributorBaseDao iDistributorBaseDao;
	
	/**
	  * @Description: 查询分销商列表
	  * @param  @return 
	  * @return List<Supplier>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月26日
	 */
	@Transactional(readOnly = true)
	public List<DistributorBase> findList(){
		return iDistributorBaseDao.findList();
	}
	
	/**
	  * @Description: 分页查询分销商基本信息
	  * @param  @param page
	  * @param  @param distributorBase
	  * @param  @return 
	  * @return Page<DistributorBase>  
	  * @throws
	  * @author LJW
	  * @date 2016年6月14日
	 */
	@Transactional(readOnly = true)
	public Page<DistributorBase> findPages(Page<DistributorBase> page, DistributorBase distributorBase) {
		distributorBase.setPage(page);
		page.setList(iDistributorBaseDao.findList(distributorBase));
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
		return iDistributorBaseDao.findMax();
	}

	/**
	 * @Description:新增分销商客商信息
	 * @param supplierBase
	 * @author WQH
	 * @date 2016年6月14日
	 */
	@Transactional(readOnly = false)
	public void saveInfo(DistributorBase distributorBase) {
		distributorBase.setIsNewRecord(true);
		distributorBase.preInsert();
		distributorBase.setCreate_user(distributorBase.getCreateBy().getName());
		distributorBase.setCreate_time(DateTool.uf_DateTimeToLongString(distributorBase.getCreateDate()));
		iDistributorBaseDao.saveInfo(distributorBase);
	}
	
	
	/**
	  * @Description: 更新分销商基本信息
	  * @param  @param supplierBase 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年6月14日
	 */
	@Transactional(readOnly = false)
	public void updateDisBase(DistributorBase distributorBase){
		distributorBase.preUpdate();
		distributorBase.setModify_user(distributorBase.getUpdateBy().getName());
		distributorBase.setModify_time(DateTool.uf_DateTimeToLongString(distributorBase.getUpdateDate()));
		iDistributorBaseDao.updateDisBase(distributorBase);
	}
	
	/**
	  * @Description:  逻辑删除 del_flag=1
	  * @param  @param distributorBase 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年6月15日
	 */
	@Transactional(readOnly = false)
	public int delDisBase(DistributorBase distributorBase){
		distributorBase.preUpdate();
		distributorBase.setModify_user(distributorBase.getUpdateBy().getName());
		distributorBase.setModify_time(DateTool.uf_DateTimeToLongString(distributorBase.getUpdateDate()));
		return iDistributorBaseDao.delDisBase(distributorBase);
	}
	
	/**
	 * 查询单条记录
	 */
	public DistributorBase finDistributorBase(DistributorBase distributorBase){
		return iDistributorBaseDao.get(distributorBase);
	}
}
