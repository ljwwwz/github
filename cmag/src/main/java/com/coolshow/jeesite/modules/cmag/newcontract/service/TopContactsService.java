package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IConFollowDao;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.ITopContactsDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ConFollow;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.TopContract;
/**
 * @Description 常用联系人业务操作类
 * ClassName: ConFollowService
 * @author LJW
 * @date 2016年5月12日
 */
@Service
@Transactional(readOnly = true)
public class TopContactsService extends CrudService<ITopContactsDao, TopContract>{
	
	@Autowired
	private ITopContactsDao iTopContactsDao;
	
	/**
	  * @Description: 查询常用联系人列表
	  * @param  @param topContract
	  * @param  @return 
	  * @return List<TopContract>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月20日
	 */
	@Transactional(readOnly = true)
	public List<TopContract> findTopContracts(TopContract topContract){
		return iTopContactsDao.findList(topContract);
	}

	/** 
	 * 	@Description:保存常用联系人信息
	 * 	@author WQH
	 * 	@Date 2016年5月17日
	 */
	@Transactional(readOnly = false)
	public void insTopCon(List<TopContract> topContract){
		iTopContactsDao.insTopCon(topContract);
		//userDao.topConInsTrac(topContract);
	}
	
	
	/** 
	 * 	@Description:将常用联系人信息保存至履行跟踪表
	 * 	@author WQH
	 * 	@Date 2016年5月17日
	 */
	@Transactional(readOnly = false)
	public void topConInsTrac(List<ConFollow> ConFollow){
		iTopContactsDao.topConInsTrac(ConFollow);
	}
	
	/**
	 * 	@Description:删除常用联系人信息
	 * 	@author WQH
	 * 	@Date 2016年5月25日
	 */
	@Transactional(readOnly = false)
	public void delTopCon(List<TopContract> topContract) {
		iTopContactsDao.delTopCon(topContract);
	}
}
