/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.coolshow.jeesite.modules.cmag.conmanger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.persistence.Page;
import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.frm.jeesite.modules.sys.dao.UserDao;
import com.coolshow.frm.jeesite.modules.sys.entity.User;
import com.coolshow.jeesite.common.datagrid.DataRequest;
import com.coolshow.jeesite.common.datagrid.DataResponse;
import com.coolshow.jeesite.common.tool.DateTool;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IContractDataDao;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IEnclosureDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.DistributorBase;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Enclosure;

/**
 * 合同维护管理系统业务操作类
 * @author ljw
 * @version 2016-05-03
 */
@SuppressWarnings("unused")
@Service
@Transactional(readOnly = true)
public class ConManagerDataService extends CrudService<IContractDataDao, Contract> {

	@Autowired
	private IContractDataDao iContractDataDao;
	@Autowired
	private IEnclosureDao iEnclosureDao;
	
	
	
	/**
	 * 根据附件编号查出合同信息
	 * @param enclosureId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Contract getContractByEnclosureId(String enclosureId){
		
		return iContractDataDao.findConByEnclosureId(enclosureId);
	}
	
	/**
	 * 无分页查询合同信息
	 */
	@Transactional(readOnly = true)
	public List<Contract> findList(Contract contract) {
		List<Contract> list = iContractDataDao.findList(contract);
		return list;
	}
	
	/**
	  * @Description: 分页查询 合同信息
	  * @param  @param page
	  * @param  @param contract
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author L
	  * @date 2016年5月10日
	 */
	@Transactional(readOnly = true)
	public Page<Contract> findContracts(Page<Contract> page, Contract contract) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		//contract.getSqlMap().put("dsf", dataScopeFilter(contract.getCurrentUser(), "o", "a"));
		contract.setPage(page);
		page.setList(iContractDataDao.findList(contract));
		return page;
	}
	
	/**
	  * @Description: 分页查询 合同信息
	  * @param  @param page
	  * @param  @param contract
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author L
	  * @date 2016年5月10日
	 */
	@Transactional(readOnly = true)
	public List<Contract> findContracts(Contract contract) {
		return iContractDataDao.findList(contract);
	}
	
	/**
	  * @Description: 分页查询合同创建人的合同信息
	  * @param  @param page
	  * @param  @param contract
	  * @param  @return 
	  * @return Page<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月26日
	 */
	@Transactional(readOnly = true)
	public Page<Contract> findNewList(Page<Contract> page,Contract contract){
		contract.setPage(page);
		page.setList(iContractDataDao.findNewList(contract));
		return page;
	}
	
	/**
	  * @Description: 分页查询合同创建人的合同信息
	  * @param  @param page
	  * @param  @param contract
	  * @param  @return 
	  * @return Page<Contract>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月26日
	 */
	@Transactional(readOnly = true)
	public List<Contract> findNewList(Contract contract){
		return iContractDataDao.findNewList(contract);
	}
	
	/**
	  * @Description: 管理角色，查询所有合同
	  * @param  @param request
	  * @param  @param tmplistAll
	  * @param  @return 
	  * @return DataResponse  
	  * @throws
	  * @author LJW
	  * @date 2016年6月23日
	 */
	@SuppressWarnings("rawtypes")
	public DataResponse search(DataRequest request,List<Contract>tmplistAll){
		DataResponse response = new DataResponse();
		int count = tmplistAll.size();// 总记录数
		List<Contract> list = tmplistAll;
		response = getDataResponse(count, list, request);
		return response;
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public DataResponse findLimitList(DataRequest request,List<Contract> contracts){
		DataResponse response = new DataResponse();
		int count = contracts.size();// 总记录数
		response = getDataResponse(count, contracts, request);
		return response;
	}
	
	/**
	  * @Description: 查询详细的合同信息
	  * @param  @param contract
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author L
	  * @date 2016年5月10日
	 */
	@Transactional(readOnly = true)
	public Contract findContractInfo(Contract contract){
		return iContractDataDao.findContractInfo(contract);
	}
	
	/**
	  * @Description: 生效确认操作
	  * @param  @param contract 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = false)
	public int updateContractSate(Contract contract) {
		contract.preUpdate();
		return iContractDataDao.updateContractSate(contract);
	}
	
	/**
	  * @Description: 修改合同信息
	  * @param  @param contract 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = false)
	public void updateContractInfo(Contract contract){
		contract.preUpdate();
		contract.setModify_user(contract.getUpdateBy().getName());
		contract.setModify_time(DateTool.uf_DateTimeToLongString(contract.getUpdateDate()));
		iContractDataDao.updateContractInfo(contract);
	}
	
	/**
	  * @Description: 分页查询 附件信息
	  * @param  @param page
	  * @param  @param enclosure
	  * @param  @return 
	  * @return Page<Enclosure>  
	  * @throws
	  * @author LJW
	  * @date 2016年5月19日
	 */
	@Transactional(readOnly = true)
	public Page<Enclosure> findEnclosures(Page<Enclosure> page,Enclosure enclosure){
		enclosure.setPage(page);
		page.setList(iEnclosureDao.findList(enclosure));
		return page;
	}

	@SuppressWarnings("rawtypes")
	private DataResponse getDataResponse(int count,List<Contract> list,DataRequest request){
		DataResponse<Contract> response = new DataResponse<Contract>();
		int limit = request.getRows() <= 0 ? 10 : request.getRows();// 每页显示数量
		int totalPages;// 总页数
		int page = request.getPage() <= 0 ? 1 : request.getPage();// 当前显示页码
		totalPages = count / limit;
		if (count % limit != 0) {
			totalPages++;
		}
		int currPage = Math.min(totalPages, page);
		int start = currPage * limit - limit;
		start = start < 0 ? 0 : start;
		response.setRecords(count);
		response.setTotal(totalPages);
		response.setPage(currPage);
		response.setRows(list);
		return response;
    } 
}