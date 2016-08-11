package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.RelationContract;

/**
 * @Description关联合同数据库操作类
 * ClassName: IConFollowDao
 * @author LJW
 * @date 2016年5月12日
 */
@MyBatisDao
public interface IRelationConDao extends CrudDao<RelationContract>{

	/**
	  * @Description: 批量插入
	  * @param  @param relationContracts
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年5月31日
	 */
	public int insertList(List<RelationContract> relationContracts);
	
	/**
	  * @Description: 查询关联合同总数
	  * @param  @param relationContract
	  * @param  @return 
	  * @return Long  
	  * @throws
	  * @author LJW
	  * @date 2016年5月31日
	 */
	public Long getCount(RelationContract relationContract);

	/**
	  * @Description: 根据合同编号删除关联合同信息
	  * @param  @param relationContract
	  * @param  @return 
	  * @return List<RelationContract>  
	  * @throws
	  * @author  WQH
	  * @date 2016年6月8日
	 */
	public void del(String contract_no);
}
