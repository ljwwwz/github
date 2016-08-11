package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IConFollowDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ConFollow;

/**
 * @Description 合同履行跟踪人业务操作类
 * ClassName: ConFollowService
 * @author LJW
 * @date 2016年5月12日
 */
@Service
@Transactional(readOnly = true)
public class ConFollowService extends CrudService<IConFollowDao, ConFollow>{
	
	@Autowired
	private IConFollowDao iConFollowDao;
	
	/**
	  * @Description: 批量插入
	  * @param  @param conFollow 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = false)
	public void saveConFollow(List<ConFollow> conFollow) {
		iConFollowDao.insertList(conFollow);
	}

	/**
	  * @Description: 批量删除
	  * @param  @param conFollows 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月23日
	 */
	@Transactional(readOnly = false)
	public void delConFollow(List<ConFollow> conFollows){
		iConFollowDao.delList(conFollows);
	}
	
	/**
	  * @Description: 批量更新
	  * @param  @param conFollows 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月23日
	 */
	@Transactional(readOnly = false)
	public void updateConFollow(Map<String,Object> conFollows){
		iConFollowDao.updateList(conFollows);
	}
	
	/**
	  * @Description: 循环更新多个纪录
	  * @param  @param conFollows 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月23日
	 */
	@Transactional(readOnly = false)
	public void updateConFollow(List<ConFollow> conFollows){
		for(ConFollow conFollow:conFollows){
			iConFollowDao.update(conFollow);
		}
	}
	
	/**
	  * @Description: 根据合同创建人id查询记录总数 
	  * @param  @param conFollow
	  * @param  @return 
	  * @return long  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = true)
	public long findCowFollowCount(ConFollow conFollow){
		return iConFollowDao.findCowFollowCount(conFollow);
	}
	
	/**
	  * @Description: 根据合同编号查询合同跟踪人列表
	  * @param  @param conFollow
	  * @param  @return 
	  * @return List<ConFollow>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = true)
	public List<ConFollow> findAllList(ConFollow conFollow){
		return iConFollowDao.findAllList(conFollow);
	}
	
	/**
	  * @Description: 根据合同编号删除履行跟踪人
	  * @author WQH
	  * @date 2016年6月8日
	 */
	@Transactional(readOnly = false)
	public void delByConNo(String old_contract_no) {
		iConFollowDao.delByConNo(old_contract_no);
	}
	
	/**
	  * @Description: 编辑操作查询履行跟踪表是否已存在记录 
	  * @param  @param conFollow
	  * @param  @return 
	  * @return long  
	  * @throws
	  * @author WQH
	  * @date 2016年6月17日
	 */
	@Transactional(readOnly = true)
	public long findRecord(ConFollow conFollow) {
		return iConFollowDao.findRecord(conFollow);
	}
}
