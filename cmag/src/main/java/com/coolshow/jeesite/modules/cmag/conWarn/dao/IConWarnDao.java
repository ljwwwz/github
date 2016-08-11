package com.coolshow.jeesite.modules.cmag.conWarn.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.conWarn.entity.ContractWarn;

/**
 * @Description 合同履行跟踪人数据库操作类
 * ClassName: IConWarnDao
 * @author LJW
 * @date 2016年7月5日
 */
@MyBatisDao
public interface IConWarnDao extends CrudDao<ContractWarn>{
	
	/**
	  * @Description: 根据提醒人id查询是否存在与提醒人列表中
	  * @param  @param contractWarn
	  * @param  @return 
	  * @return long  
	  * @throws
	  * @author LJW
	  * @date 2016年7月6日
	 */
	public long findCount(ContractWarn contractWarn);

	/**
	  * @Description: 查询合同提醒信息列表
	  * @param  @return 
	  * @return List<ContractWarn>  
	  * @throws
	  * @author LJW
	  * @date 2016年7月10日
	 */
	public List<ContractWarn> findAllListNoWhere();

	/**
	  * @Description: 批量插入多条合同提醒记录
	  * @param  @param contractWarns
	  * @param  @return 
	  * @return ContractWarn  
	  * @throws
	  * @author WQH
	  * @date 2016年7月15日
	 */
	public void insertList(List<ContractWarn> contractWarns);
	
	/**
	  * @Description: 更新邮件发送状态
	  * @param  @param contractWarn 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年7月27日
	 */
	public int updateState(ContractWarn contractWarn);
}
