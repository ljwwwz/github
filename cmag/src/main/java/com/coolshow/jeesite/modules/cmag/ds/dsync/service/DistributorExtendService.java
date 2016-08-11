package com.coolshow.jeesite.modules.cmag.ds.dsync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.dsyn.dc.model.CRMCmObjectDc;
import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.ds.dsync.dao.DistributorExtendDao;
import com.coolshow.jeesite.modules.cmag.ds.dsync.entity.SimpleDistributor;

@Service
public class DistributorExtendService extends CrudService<DistributorExtendDao, SimpleDistributor> {

	@Autowired
	protected DistributorExtendDao dao;

	/**
	 * 根据客商编码查询客商
	 * 
	 * @param ksCode
	 *            客商编码
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean exitCmagDc(String ksCode) {
		SimpleDistributor distributor = dao.get(ksCode);
		if (distributor != null) {
			return true;
		}
		return false;
	}

	/**
	 * 插入一条分销商信息
	 * 
	 * @param cmagDc
	 * @return
	 */
	@Transactional(readOnly = false)
	public int insertCmagDc(CRMCmObjectDc cmagDc) {

		SimpleDistributor distributor = new SimpleDistributor();
		distributor.setDistributorNo(cmagDc.getKsCode());
		distributor.setName(cmagDc.getKsName());
		distributor.setCreateDate(cmagDc.getCreateTime());
		distributor.setDistributorAccount("");
		distributor.setDelFlag("0");

		return dao.insert(distributor);
	}

	/**
	 * 更新分销商信息
	 * 
	 * @param cmagDc
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateCmagDc(CRMCmObjectDc cmagDc) {

		SimpleDistributor distributor = new SimpleDistributor();
		distributor.setDistributorNo(cmagDc.getKsCode());
		distributor.setName(cmagDc.getKsName());
		distributor.setModifyTime(cmagDc.getUpdateTime());

		return dao.update(distributor);
	}

	/**
	 * 删除分销商数据
	 * 
	 * @param cmagDc
	 * @return
	 */
	@Transactional(readOnly = false)
	public int deleteCmagDc(CRMCmObjectDc cmagDc) {
		SimpleDistributor distributor = new SimpleDistributor();
		distributor.setDistributorNo(cmagDc.getKsCode());

		return dao.delete(distributor);
	}

}
