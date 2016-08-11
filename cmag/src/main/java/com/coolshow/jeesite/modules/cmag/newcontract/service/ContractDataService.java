/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.persistence.Page;
import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IContractDataDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;

/**
 * @Description合同基本信息业务类
 * ClassName: ContractDataService
 * @author LJW
 * @date 2016年5月11日
 */
@Service
@Transactional(readOnly = true)
public class ContractDataService extends CrudService<IContractDataDao, Contract> {

	@Autowired
	private IContractDataDao iContractDataDao;
	
	/**
	 * 查询合同列表
	 */
	public List<Contract> findList(Contract contract) {
		return super.findList(contract);
	}
	
	/**
	  * @Description: 保存合同信息
	  * @param  @param contract 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = false)
	public void saveContract(Contract contract) {
		iContractDataDao.insert(contract);
	}
	
	/**
	  * @Description: 根据合同编号查询合同信息
	  * @param  @param contract_no
	  * @param  @return 
	  * @return Contract  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = true)
	public Contract findContract(String contract_no){
		return iContractDataDao.findContract(contract_no);
	}
	
	/**
	  * @Description: 根据合同编号更新合同信息 
	  * @param  @param contract 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = false)
	public void updateContractInfo(Contract contract){
		iContractDataDao.updateContractInfo(contract);
	}
	
	/**
	  * @Description: 查询合同列表
	  * @param  @return 
	  * @return List<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月30日
	 */
	@SuppressWarnings("deprecation")
	@Transactional(readOnly = true)
	public List<Contract>findContracts(){
		return iContractDataDao.findAllList();
	}
	
	/**
	  * @Description: 分页查询关联合同信息
	  * @param  @param contract
	  * @param  @return 
	  * @return List<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月31日
	 */
	@Transactional(readOnly = true)
	public List<Contract> findRelationList(Contract contract){
		return iContractDataDao.findRelationList(contract);
	}
	
	/**
	  * @Description: 分页查询所有合同信息  数据权限：管理员  超级管理员角色
	  * @param  @param page
	  * @param  @param contract
	  * @param  @return 
	  * @return Page<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年6月16日
	 */
	@Transactional(readOnly = true)
	public List<Contract> findAllCon(Contract contract){
		return iContractDataDao.findAllCon(contract);
	}
	
	/**
	  * @Description: 根据合同编号查询关联合同信息
	  * @param  @param contract
	  * @param  @return 
	  * @return List<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年6月7日
	 */
	@Transactional(readOnly = true)
	public List<Contract> findRelationAllList(Contract contract){
		return iContractDataDao.findRelationList(contract);
	}
	
	/**
	  * @Description: 批量插入
	  * @param  @param contracts 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年6月1日
	 */
	@Transactional(readOnly = false)
	public void insertList(List<Contract> contracts)throws Exception{
		iContractDataDao.insertList(contracts);
	}
	
	/**
	  * @Description: 根据合同编号统计总数
	  * @param  @param contract
	  * @param  @return 
	  * @return long  
	  * @throws
	  * @author LJW
	  * @date 2016年6月7日
	 */
	@Transactional(readOnly = true)
	public long findCount(Contract contract){
		return iContractDataDao.findCount(contract);
	}
	
	/**
	  * @Description: 查询所属省份下供应商/分销商 合同编号最大值 
	  * @param  @param contract
	  * @param  @return 
	  * @return long  
	  * @throws
	  * @author LJW
	  * @date 2016年6月27日
	 */
	@Transactional(readOnly = true)
	public String findMaxContractNo(Contract contract){
		return iContractDataDao.findMaxContractNo(contract);
	}
	
	/**
	  * @Description: 根据合同编号更新合同归档信息
	  * @param  @param contract
	  * @param  @return 
	  * @throws
	  * @author WQH
	  * @date 2016年7月1日
	 */
	@Transactional(readOnly = false)
	public void updateArchivedInfo(Contract contract){
		iContractDataDao.updateArchivedInfo(contract);
	}
	
	/**
	  * @Description: 合同提醒查询列表
	  * @param  @param contract
	  * @param  @return 
	  * @return List<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年7月6日
	 */
	@Transactional(readOnly = true)
	public List<Contract> findConWarnList(Contract contract){
		return iContractDataDao.findConWarnList(contract);
	}
	
	/**
	  * @Description: 根据合同编号更新合同归档信息
	  * @param  @param contract
	  * @param  @return 
	  * @throws
	  * @author WQH
	  * @date 2016年7月1日
	 */
	@Transactional(readOnly = false)
	public int updateContractWarnSate(Contract contract){
		return iContractDataDao.updateContractWarnSate(contract);
	}
}