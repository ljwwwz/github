package com.coolshow.jeesite.modules.cmag.newcontract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.persistence.Page;
import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IVerifyInfoDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.VerifyInfo;

/**
 * @Description 合同审核业务操作类
 * ClassName: VerifyInfoService.java
 * @author LJW
 * @date 下午8:20:56
 */
@Service
@Transactional(readOnly = true)
public class VerifyInfoService extends CrudService<IVerifyInfoDao, VerifyInfo>{

	@Autowired
	private IVerifyInfoDao iVerifyInfoDao;
	
	/**
	 * 保存
	 */
	@Transactional(readOnly = false)
	public void save(VerifyInfo verifyInfo){
		verifyInfo.setIsNewRecord(true);
		verifyInfo.preInsert();
		verifyInfo.setVerify_person(verifyInfo.getCreateBy().getName());
		verifyInfo.setVerify_date(verifyInfo.getCreateDate());
		iVerifyInfoDao.insert(verifyInfo);
	}
	
	/**
	  * @Description: 分页查询 合同意见信息
	  * @param  @param page
	  * @param  @param enclosure
	  * @param  @return 
	  * @return Page<VerifyInfo>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月19日
	 */
	@Transactional(readOnly = true)
	public Page<VerifyInfo> findVerifyInfos(Page<VerifyInfo> page,VerifyInfo verifyInfo){
		verifyInfo.setPage(page);
		page.setList(iVerifyInfoDao.findList(verifyInfo));
		return page;
	}	
}
