package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.VerifyInfo;

/**
 * @Description 合同审核信息据库操作类
 * ClassName: IVerifyInfoDao
 * @author LJW
 * @date 2016年5月10日
 */
@MyBatisDao
public interface IVerifyInfoDao extends CrudDao<VerifyInfo>{

}
