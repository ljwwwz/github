package com.coolshow.jeesite.modules.cmag.newcontract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IScanningDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Scanning;

@Service
@Transactional(readOnly = true)
public class ScanningService extends CrudService<IScanningDao, Scanning>{

	@Autowired
	private IScanningDao iScanningDao;
	
	/**
	  * @Description: 插入扫描件信息
	  * @param  @param scanning 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月19日
	 */
	@Transactional(readOnly = false)
	public void insertScanning(Scanning scanning){
		iScanningDao.insert(scanning);
	}
}
