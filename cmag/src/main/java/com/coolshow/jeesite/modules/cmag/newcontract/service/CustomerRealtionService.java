package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.ICustomerRelationDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.CustomerRelation;

/**
 * @Description 合同主体关联中间表业务操作类
 * ClassName: CustomerRealtionService
 * @author LJW
 * @date 2016年5月12日
 */
@Service
@Transactional(readOnly = true)
public class CustomerRealtionService extends CrudService<ICustomerRelationDao, CustomerRelation>{
	
	@Autowired
	private ICustomerRelationDao iCustomerRelationDao;
	
	/**
	  * @Description: 批量插入
	  * @param  @param conFollow 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = false)
	public void saveCustomerRealtion(List<CustomerRelation> customerRelations) {
		iCustomerRelationDao.insertList(customerRelations);
	}

}
