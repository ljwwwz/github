package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;
import java.util.Map;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ConFollow;

/**
 * @Description 合同履行跟踪人数据库操作类
 * ClassName: IConFollowDao
 * @author LJW
 * @date 2016年5月12日
 */
@MyBatisDao
public interface IConFollowDao extends CrudDao<ConFollow>{

	/**
	  * @Description: 批量插入合同履行跟踪人
	  * @param  @param conFollows 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	public void insertList(List<ConFollow> conFollows);
	
	/**
	  * @Description: 批量删除合同履行跟踪人
	  * @param  @param conFollows
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年5月23日
	 */
	public int delList(List<ConFollow> conFollows);
	
	/**
	  * @Description: 批量更新合同履行跟踪人
	  * @param  @param conFollows
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年5月23日
	 */
	public int updateList(Map<String,Object>conFollows);
	
	/**
	  * @Description: 更新单条记录
	  * @param  @param conFollow
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年5月23日
	 */
	public int updateConFollow(ConFollow conFollow);
	
	/**
	  * @Description: 根据合同创建人id查询记录总数
	  * @param  @param conFollow
	  * @param  @return 
	  * @return long  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	public long findCowFollowCount(ConFollow conFollow);

	/**
	  * @Description: 根据合同编号删除履行跟踪人
	  * @author WQH
	  * @date 2016年6月8日
	 */
	public void delByConNo(String old_contract_no);

	/**
	  * @Description: 编辑操作查询履行跟踪表是否已存在记录 
	  * @param  @param conFollow
	  * @param  @return 
	  * @return long  
	  * @throws
	  * @author WQH
	  * @date 2016年6月17日
	 */
	public long findRecord(ConFollow conFollow);
}
