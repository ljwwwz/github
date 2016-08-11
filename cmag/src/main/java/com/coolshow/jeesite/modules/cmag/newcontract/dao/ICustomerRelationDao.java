package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.CustomerRelation;

/**
 * @Description 合同主体关联中间表据库操作类
 * ClassName: ICustomerRelationDao
 * @author LJW
 * @date 2016年5月12日
 */
@MyBatisDao
public interface ICustomerRelationDao extends CrudDao<CustomerRelation>{

	/**
	  * @Description: 批量插入合同履行跟踪人
	  * @param  @param conFollows 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	public void insertList(List<CustomerRelation> customerRelations);
	
}
