package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ContractState;

@MyBatisDao
public interface IContractStateDao extends CrudDao<ContractState>{
	
	public ContractState getContractStateByState_dm(String dm);
	
	public List<ContractState> getAllContractState();
	
}
