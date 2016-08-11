package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Distributor;

/**
 * @Description 分销商数据库操作类
 * ClassName: IDistributorDao
 * @author LJW
 * @date 2016年5月10日
 */
@MyBatisDao
public interface IDistributorDao extends CrudDao<Distributor>{
	
	/**
	 * @Description: 查询分销商信息
	 * @param @param distributor
	 * @param @return   
	 * @return Distributor  
	 * @throws
	 * @author LJW
	 * @date 2016年5月11日
	 */
	public Distributor findDistributor(String contract_no);

	
	/**
	  * @Description: 根据合同编号更新分销商合同信息
	  * @param  @param distributor
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	public int updateDistributor(Distributor distributor);
	
	
	/**
	  * @Description: 批量插入
	  * @param  @param distributors
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年6月1日
	 */
	public int insertList(List<Distributor> distributors);
}
