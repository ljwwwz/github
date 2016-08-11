package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.CheckContractLog;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;

@MyBatisDao
public interface ICheckContractLogDao extends CrudDao<CheckContractLog>{

	public ICheckContractLogDao getByUuid(String uuid);
	
	public Contract get1(String id);
	
	public List<Contract> findAllList1();
	
	public void delete1(Contract contract);
	
	public List<CheckContractLog> getAllEntity();
	
	public List<CheckContractLog> getAllEntityBYContractNo(String contract_no);
}
