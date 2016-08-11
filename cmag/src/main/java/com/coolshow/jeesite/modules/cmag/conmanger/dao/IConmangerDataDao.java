/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.coolshow.jeesite.modules.cmag.conmanger.dao;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;

/**
 * conmangerDAO接口
 * @author ljw
 * @version 2016-05-03
 */
@MyBatisDao
public interface IConmangerDataDao extends CrudDao<Contract> {
	
}