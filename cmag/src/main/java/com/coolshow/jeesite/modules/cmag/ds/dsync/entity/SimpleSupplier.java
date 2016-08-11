package com.coolshow.jeesite.modules.cmag.ds.dsync.entity;

import java.util.Date;

import com.coolshow.frm.jeesite.common.persistence.TreeEntity;

/**
 * @Description 供应商基本信息
 * ClassName: Supplier.java
 * @author ay_lin
 * 
 */
public class SimpleSupplier extends TreeEntity<SimpleSupplier> {

	private static final long serialVersionUID = 1L;
	
	private String supplierNo;			//供应商编号	
	private String name;                //供应商名称
	private Date createTime;			//创建时间	
	private Date modifyTime;			//修改时间
	//private Integer delFlag;            //逻辑删除标记 0:未删除  1：删除
	private String supplierAccount;     //供应商账号
	
	public SimpleSupplier() {
		super();
	}
	
	@Override
	public void setParent(SimpleSupplier parent) {
		this.parent = parent;
		
	}

	@Override
	public SimpleSupplier getParent() {
		return parent;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getSupplierAccount() {
		return supplierAccount;
	}

	public void setSupplierAccount(String supplierAccount) {
		this.supplierAccount = supplierAccount;
	}

}
