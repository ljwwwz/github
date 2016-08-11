package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Scanning;

/**
 * @Description 扫描件数据库操作类
 * ClassName: IScanningDao.java
 * @author LJW
 * @date 上午10:17:45
 */
@MyBatisDao
public interface IScanningDao extends CrudDao<Scanning>{
	
	
}
