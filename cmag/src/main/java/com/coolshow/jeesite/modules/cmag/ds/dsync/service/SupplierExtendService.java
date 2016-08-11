package com.coolshow.jeesite.modules.cmag.ds.dsync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.dsyn.dc.model.CRMCmObjectDc;
import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.ds.dsync.dao.SupplierExtendDao;
import com.coolshow.jeesite.modules.cmag.ds.dsync.entity.SimpleSupplier;

@Service
public class SupplierExtendService extends CrudService<SupplierExtendDao, SimpleSupplier> {

	@Autowired
	protected SupplierExtendDao dao;

	/**
	 * 根据供应商编码查询供应商
	 * @param ksCode
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean exitCmagDc(String ksCode) {
		SimpleSupplier supplier = dao.get(ksCode);
		if (supplier != null) {
			return true;
		}
		return false;
	}

	/**
	 * 插入一条供应商信息
	 * @param cmagDc
	 * @return
	 */
	@Transactional(readOnly = false)
	public int insertCmagDc(CRMCmObjectDc cmagDc) {

		SimpleSupplier supplier = new SimpleSupplier();
		supplier.setSupplierNo(cmagDc.getKsCode());
		supplier.setName(cmagDc.getKsName());
		supplier.setCreateDate(cmagDc.getCreateTime());
		supplier.setDelFlag("0");
		supplier.setSupplierAccount("");
		return dao.insert(supplier);

	}

	/**
	 * 更新供应商信息
	 * @param cmagDc
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateCmagDc(CRMCmObjectDc cmagDc) {

		SimpleSupplier supplier = new SimpleSupplier();
		supplier.setSupplierNo(cmagDc.getKsCode());
		supplier.setName(cmagDc.getKsName());
		supplier.setModifyTime(cmagDc.getUpdateTime());

		return dao.update(supplier);

	}

	/**
	 * 删除供应商数据
	 * @param cmagDc
	 * @return
	 */
	@Transactional(readOnly = false)
	public int deleteCmagDc(CRMCmObjectDc cmagDc) {
		SimpleSupplier supplier = new SimpleSupplier();
		supplier.setSupplierNo(cmagDc.getKsCode());

		return dao.delete(supplier);

	}

}
