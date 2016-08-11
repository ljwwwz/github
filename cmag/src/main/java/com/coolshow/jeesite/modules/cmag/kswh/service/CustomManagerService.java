/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.coolshow.jeesite.modules.cmag.kswh.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.persistence.Page;
import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.frm.jeesite.modules.sys.dao.UserDao;
import com.coolshow.frm.jeesite.modules.sys.entity.User;
import com.coolshow.jeesite.common.datagrid.DataRequest;
import com.coolshow.jeesite.common.datagrid.DataResponse;
import com.coolshow.jeesite.common.tool.ContractTypeEnum;
import com.coolshow.jeesite.common.tool.DateTool;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IContractDataDao;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IDistributorBaseDao;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IEnclosureDao;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.ISupplierBaseDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Contract;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.DistributorBase;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Enclosure;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.SupplierBase;

/**
 * 合同主体维护业务操作类
 * @author ljw
 * @version 2016-05-03
 * @param <T>
 */
@SuppressWarnings("unused")
@Service
@Transactional(readOnly = true)
public class CustomManagerService{

	@Autowired
	private ISupplierBaseDao iSupplierBaseDao;
	@Autowired
	private IDistributorBaseDao iDistributorBaseDao;
	
	/**
	  * @Description: 供应商
	  * @param  @param request
	  * @param  @param supplierBase
	  * @param  @return 
	  * @return DataResponse  
	  * @throws
	  * @author LJW
	  * @date 2016年6月23日
	 */
    @SuppressWarnings({"rawtypes" })
	public DataResponse searchSup(DataRequest request,SupplierBase supplierBase) {  
        DataResponse response = new DataResponse(); 
        int count = iSupplierBaseDao.findList().size();  
        List<SupplierBase> list = iSupplierBaseDao.findListLimit(supplierBase);
        response = getDataResponse(count,list,request);
        return response;  
    }

    /**
      * @Description: 分销商
      * @param  @param request
      * @param  @param distributorBase
      * @param  @return 
      * @return DataResponse  
      * @throws
      * @author LJW
      * @date 2016年6月23日
     */
    @SuppressWarnings({"rawtypes" })
	public DataResponse searchDis(DataRequest request,DistributorBase distributorBase) {  
        DataResponse response = new DataResponse();  
        int count = iDistributorBaseDao.findList().size();//总记录数  
        List<DistributorBase> list  = iDistributorBaseDao.findListLimit(distributorBase);
        response = getDataResponse(count,list,request);
        return response;  
    }
    
    @SuppressWarnings("rawtypes")
	private <E> DataResponse getDataResponse(int count,List<E> list,DataRequest request){
		DataResponse<E> response = new DataResponse<E>();
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