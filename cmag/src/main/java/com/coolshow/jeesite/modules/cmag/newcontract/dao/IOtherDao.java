package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Other;

/**
 * @Description 其他合同数据库操作类
 * ClassName: IOtherDao
 * @author WQH
 * @date 2016年6月3日
 */
@MyBatisDao
public interface IOtherDao extends CrudDao<Other>{

	/**
	  * @Description: 查询其他信息列表
	  * @param  @return 
	  * @return List<Other>  
	  * @throws
	  * @author LJW
	  * @date 2016年6月6日
	 */
	public List<Other> findList();
	
	/**
	 * 查询单条记录 
	 */
	public Other get(Other other);
}
