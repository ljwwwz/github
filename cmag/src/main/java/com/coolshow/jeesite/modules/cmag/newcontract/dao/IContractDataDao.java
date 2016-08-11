/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;

/**
 * @Description 合同基本信息数据库操作类
 * ClassName: IContractDataDao
 * @author LJW
 * @date 2016年5月11日
 */
@MyBatisDao
public interface IContractDataDao extends CrudDao<Contract> {
	
	/**
	 * @Description: 查询合同基本信息
	 * @param @param contract
	 * @param @return   
	 * @return Contract  
	 * @throws
	 * @author LJW
	 * @date 2016年5月11日
	 */
	public Contract findContract(String contract_no);
	
	/**
	  * @Description: 查询详细的合同信息
	  * @param  @param contract
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author L
	  * @date 2016年5月10日
	 */
	public Contract findContractInfo(Contract contract);
	
	/**
	  * @Description: 生效确认操作 更新合同状态
	  * @param  @param contract
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	public int updateContractSate(Contract contract);
	
	/**
	  * @Description: 根据合同编号更新合同提醒状态
	  * @param  @param contract
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年7月10日
	 */
	public int updateContractWarnSate(Contract contract);
	
	/**
	  * @Description: 根据合同编号更新合同信息
	  * @param  @param contract
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	public int updateContractInfo(Contract contract);
	
	/**
	  * @Description: 分页查询合同创建人的合同信息
	  * @param  @param contract
	  * @param  @return 
	  * @return List<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月26日
	 */
	public List<Contract> findNewList(Contract contract);
	
	/**
	  * @Description: 分页查询所有合同信息
	  * @param  @param contract
	  * @param  @return 
	  * @return List<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年6月16日
	 */
	public List<Contract> findAllCon(Contract contract);
	
	/**
	  * @Description: 查询关联合同信息
	  * @param  @param contract
	  * @param  @return 
	  * @return List<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月31日
	 */
	public List<Contract>findRelationList(Contract contract);
	
	/**
	  * @Description: 批量插入
	  * @param  @param contracts
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年6月1日
	 */
	public int insertList(List<Contract> contracts);
	
	/**
	  * @Description: 根据合同编号统计总数
	  * @param  @param contract
	  * @param  @return 
	  * @return long  
	  * @throws
	  * @author LJW
	  * @date 2016年6月7日
	 */
	public long findCount(Contract contract);
	
	/**
	  * @Description: 查询所属省份下供应商/分销商 合同编号最大值 
	  * @param  @param contract
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月27日
	 */
	public String findMaxContractNo(Contract contract);
	
	/**
	  * @Description: 根据合同编号更新合同归档信息
	  * @param  @param contract
	  * @param  @return 
	  * @throws
	  * @author WQH
	  * @date 2016年7月1日
	 */
	public void updateArchivedInfo(Contract contract);
	
	/**
	  * @Description: 合同提醒查询列表
	  * @param  @param contract
	  * @param  @return 
	  * @return List<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年7月6日
	 */
	public List<Contract> findConWarnList(Contract contract);
	/**
	 * 根据附件id查找合同详细信息
	 * @param enclosureId
	 * @return
	 * @author rj_bian
	 */
	public Contract findConByEnclosureId(String enclosureId);
}