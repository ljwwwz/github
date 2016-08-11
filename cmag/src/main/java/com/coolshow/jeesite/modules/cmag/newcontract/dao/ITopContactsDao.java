package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ConFollow;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.TopContract;

/**
 * @Description 常用联系人数据库操作类
 * ClassName: IConFollowDao
 * @author LJW
 * @date 2016年5月12日
 */
@MyBatisDao
public interface ITopContactsDao extends CrudDao<TopContract>{


	/**
	 * 插入常用联系人
	 * @author WQH
	 * @return
	 */
	public int insTopCon(List<TopContract> topContract);

	
	/**
	 * 插入履行跟踪人
	 * @author WQH
	 * @return
	 */
	public void topConInsTrac(List<ConFollow> conFollow);

	/**
	 * 删除常用联系人
	 * @author WQH
	 * @return
	 */
	public void delTopCon(List<TopContract> topContract);
	
}
