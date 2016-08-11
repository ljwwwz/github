package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.ICheckContractLogDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.CheckContractLog;

/**
 *  查看合同行为记录的service类
 * @author brj
 *
 */
@Service("checkContractLogService")
@Transactional
public class CheckContractLogService extends CrudService<ICheckContractLogDao, CheckContractLog>{
	
	@Autowired
	private ICheckContractLogDao iCheckContractLogDao;
	
	/**
	 * 获取所有对合同查看行为的记录
	 * @return
	 */
	public List<CheckContractLog> getAllEntity(){
		return iCheckContractLogDao.getAllEntity();
	}
	
	/**
	 * 依照合同编号获取所有对合同查看行为的记录
	 * @return
	 */
	public List<CheckContractLog> getAllEntityBYContractNo(String contract_no){
		return iCheckContractLogDao.getAllEntityBYContractNo(contract_no);
	}
}
