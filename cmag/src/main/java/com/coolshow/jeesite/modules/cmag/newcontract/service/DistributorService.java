package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.frm.jeesite.common.utils.SpringContextHolder;
import com.coolshow.frm.jeesite.common.utils.StringUtils;
import com.coolshow.jeesite.common.tool.DateTool;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IDistributorDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Distributor;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Supplier;

/**
 * @Description 分销商合同业务操作类
 * ClassName: DistributorService.java
 * @author LJW
 * @date 下午4:57:22
 */
@Service
@Transactional(readOnly = true)
public class DistributorService extends CrudService<IDistributorDao, Distributor> {

	@Autowired
	private IDistributorDao iDistributorDao;
	
	/**
	  * @Description: 保存分销商合同信息
	  * @param  @param distributor 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = false)
	public void saveDistributorInfo(Distributor distributor){
		distributor.setIsNewRecord(true);
		distributor.preInsert();
		iDistributorDao.insert(distributor);
	}
	
	
	/**
	  * @Description: 根据合同编号查询分销商合同信息
	  * @param  @param contract_no
	  * @param  @return 
	  * @return Distributor  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = true)
	public Distributor findDistributor(String contract_no){
		Distributor distributor = dao.get(contract_no);
		return distributor;
	}
	
	/**
	  * @Description: 根据合同编号更新分销商合同信息 
	  * @param  @param distributor 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月18日
	 */
	@Transactional(readOnly = false)
	public void updateDistributor(Distributor distributor){
		distributor.preUpdate();
		distributor.setModify_user(distributor.getUpdateBy().getName());
		distributor.setModify_time(DateTool.uf_DateTimeToLongString(distributor.getUpdateDate()));
		iDistributorDao.updateDistributor(distributor);
	}
	
	/**
	  * @Description: 批量插入
	  * @param  @param distributors 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年6月1日
	 */
	@Transactional(readOnly = false)
	public void insertList(List<Distributor> distributors){
		iDistributorDao.insertList(distributors);
	}
}
