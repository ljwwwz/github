package com.coolshow.jeesite.modules.cmag.conWarn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.jeesite.common.datagrid.DataRequest;
import com.coolshow.jeesite.common.datagrid.DataResponse;
import com.coolshow.jeesite.modules.cmag.conWarn.dao.IConWarnDao;
import com.coolshow.jeesite.modules.cmag.conWarn.entity.ContractWarn;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.ToolEntity;

/**
 * 合同提醒业务操作类
 * @author LJW
 */
@Service
@Transactional(readOnly = true)
public class ContractWranService {
	
	@Autowired
	private IConWarnDao iConWarnDao;
	
	/**
	  * @Description: 根据提醒人id查询是否存在与提醒人列表中
	  * @param  @param contractWarn
	  * @param  @return 
	  * @return long  
	  * @throws
	  * @author LJW
	  * @date 2016年7月6日
	 */
	@Transactional(readOnly = true)
	public long findCount(ContractWarn contractWarn){
		return iConWarnDao.findCount(contractWarn);
	}
	
	/**
	  * @Description: 查询合同提醒信息列表
	  * @param  @return 
	  * @return List<ContractWarn>  
	  * @throws
	  * @author LJW
	  * @date 2016年7月10日
	 */
	@Transactional(readOnly = true)
	public List<ContractWarn> findAllListNoWhere(){
		return iConWarnDao.findAllListNoWhere();
	}
	
	/**
	  * @Description: 修改合同提醒内容
	  * @param  @param contractWarn
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年7月12日
	 */
	@Transactional(readOnly = false)
	public int upadateConWarn(ContractWarn contractWarn){
		return iConWarnDao.update(contractWarn);
	}
	
	/**
	  * @Description: 更新邮件发送状态
	  * @param  @param contractWarn
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年7月27日
	 */
	@Transactional(readOnly = false)
	public int updateState(ContractWarn contractWarn){
		return iConWarnDao.updateState(contractWarn);
	}
	
	/**
	  * @Description: 物理删除合同提醒内容
	  * @param  @param contractWarn
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年7月12日
	 */
	@Transactional(readOnly = false)
	public int delWarn(ContractWarn contractWarn){
		return iConWarnDao.delete(contractWarn);
	}
	
	/**
	  * @Description: 查询单条记录
	  * @param  @param contractWarn
	  * @param  @return 
	  * @return ContractWarn  
	  * @throws
	  * @author LJW
	  * @date 2016年7月12日
	 */
	@Transactional(readOnly = true)
	public ContractWarn get(ContractWarn contractWarn){
		return iConWarnDao.get(contractWarn);
	}
	
	/**
	  * @Description: 批量插入多条合同提醒记录
	  * @param  @param contractWarns
	  * @param  @return 
	  * @return ContractWarn  
	  * @throws
	  * @author WQH
	  * @date 2016年7月15日
	 */
	@Transactional(readOnly = false)	
	public void insertList(List<ContractWarn> contractWarns) {
		iConWarnDao.insertList(contractWarns);
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
	public DataResponse search(DataRequest request,List<ToolEntity>tmplistAll){
		DataResponse response = new DataResponse();
		int count = tmplistAll.size();// 总记录数
		List<ToolEntity> list = tmplistAll;
		response = getDataResponse(count, list, request);
		return response;
	}
	
	@SuppressWarnings("rawtypes")
	private DataResponse getDataResponse(int count,List<ToolEntity> list,DataRequest request){
		DataResponse<ToolEntity> response = new DataResponse<ToolEntity>();
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
