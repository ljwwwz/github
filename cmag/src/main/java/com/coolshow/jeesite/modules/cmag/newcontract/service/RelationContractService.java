package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IRelationConDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.RelationContract;

/**
 * @Description 关联合同业务操作类
 * ClassName: ConFollowService
 * @author LJW
 * @date 2016年5月12日
 */
@Service
@Transactional(readOnly = true)
public class RelationContractService extends CrudService<IRelationConDao, RelationContract>{
	
	@Autowired
	private IRelationConDao iRelationConDao;
	
	/**
	  * @Description: 插入
	  * @param  @param relationContract 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = false)
	public void save(RelationContract relationContract) {
		iRelationConDao.insert(relationContract);
	}

	/**
	  * @Description: 批量插入
	  * @param  @param rList 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月31日
	 */
	@Transactional(readOnly = false)
	public void insertList(List<RelationContract> rList){
		iRelationConDao.insertList(rList);
	}
	
	/**
	  * @Description: 查询关联合同总数
	  * @param  @param relationContract
	  * @param  @return 
	  * @return Long  
	  * @throws
	  * @author LJW
	  * @date 2016年5月31日
	 */
	@Transactional(readOnly = true)
	public Long getCount(RelationContract relationContract){
		return iRelationConDao.getCount(relationContract);
	}
	
	/**
	  * @Description: 根据合同编号查询关联合同列表
	  * @param  @param relationContract
	  * @param  @return 
	  * @return List<RelationContract>  
	  * @throws
	  * @author LJW
	  * @date 2016年6月7日
	 */
	@Transactional(readOnly = true)
	public List<RelationContract> findAllList(RelationContract relationContract){
		return iRelationConDao.findAllList(relationContract);
	}
	
	/**
	  * @Description: 根据合同编号删除关联合同信息
	  * @param  @param relationContract
	  * @param  @return 
	  * @return List<RelationContract>  
	  * @throws
	  * @author  WQH
	  * @date 2016年6月8日
	 */
	@Transactional(readOnly = false)
	public void del(String contract_no) {
		iRelationConDao.del(contract_no);
	}
}
