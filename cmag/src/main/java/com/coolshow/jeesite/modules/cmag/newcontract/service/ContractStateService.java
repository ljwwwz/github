package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IContractStateDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ContractState;

@Service("contractStateService")
@Transactional
public class ContractStateService extends CrudService<IContractStateDao, ContractState>{
	
	@Autowired
	private IContractStateDao iContractStateDao;
	
	public ContractState getContractStateByState_dm(String dm){
		
		return iContractStateDao.getContractStateByState_dm(dm);
	}
	
	public List<ContractState> getAllContractState(){
		return iContractStateDao.getAllContractState();
	}
	
	
	public IContractStateDao getiContractStateDao() {
		return iContractStateDao;
	}

	public void setiContractStateDao(IContractStateDao iContractStateDao) {
		this.iContractStateDao = iContractStateDao;
	}
	
	
	
	
	
}
