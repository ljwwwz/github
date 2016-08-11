package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coolshow.frm.jeesite.common.persistence.TreeDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.DistributorBase;

/**
 * @Description 分销商Dao
 * ClassName: IDistributorBaseDao.java
 * @author LJW
 * @date 上午8:23:25
 */
@MyBatisDao
public interface IDistributorBaseDao extends TreeDao<DistributorBase> {

	/**
	  * @Description: 查询分销商列表
	  * @param  @return 
	  * @return List<DistributorBase>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月27日
	 */
	public List<DistributorBase> findList();

	/**
	  * @Description: 分页查询
	  * @param  @param distributorBase
	  * @param  @param start
	  * @param  @param limit
	  * @param  @return 
	  * @return List<DistributorBase>  
	  * @throws
	  * @author LJW
	  * @date 2016年6月22日
	 */
	//public List<DistributorBase>findListLimit(@Param("distributorBase")DistributorBase distributorBase,@Param(value = "start")int start,@Param(value = "limit")int limit);
	public List<DistributorBase>findListLimit(@Param("distributorBase")DistributorBase distributorBase);
	
	/**
	 * @Description:查询后7位最大客商编码
	 * @return String
	 * @author WQH
	 * @date 2016年6月14日
	 */
	public String findMax();

	/**
	 * @Description:新增分销商客商信息
	 * @param supplierBase
	 * @author WQH
	 * @date 2016年6月14日
	 */
	public void saveInfo(DistributorBase distributorBase);
	
	
	/**
	  * @Description: 更新分销商信息
	  * @param  @param distributorBase
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年6月14日
	 */
	public int updateDisBase(DistributorBase distributorBase);
	
	/**
	  * @Description: 逻辑删除 del_flag=1
	  * @param  @param distributorBase
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年6月15日
	 */
	public int delDisBase(DistributorBase distributorBase);
	
	/**
	 * 查询单条记录 
	 */
	public DistributorBase get(DistributorBase distributorBase);
}
