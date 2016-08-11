package com.coolshow.jeesite.modules.cmag.ds.dsync.entity;

import java.util.Date;

import com.coolshow.frm.jeesite.common.persistence.TreeEntity;

/**
 * @Description 分销商基本信息
 * ClassName: Distributor.java
 * @author ay_lin
 */
public class SimpleDistributor extends TreeEntity<SimpleDistributor> {


	private static final long serialVersionUID = -1534467305557695342L;
	
	private String distributorNo; 				// 分销商编号
	private String name; 						// 分销商名称	
	private Date createTime;					//创建时间	
	private Date modifyTime;					//修改时间
	//private Integer delFlag;                  //逻辑删除标记 0:未删除  1：删除'
	private String distributorAccount;         // 销商分账号

	public SimpleDistributor() {
		super();
	}
	
	@Override
	public SimpleDistributor getParent() {
		return parent;
	}

	@Override
	public void setParent(SimpleDistributor parent) {
		this.parent = parent;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistributorNo() {
		return distributorNo;
	}

	public void setDistributorNo(String distributorNo) {
		this.distributorNo = distributorNo;
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

	public String getDistributorAccount() {
		return distributorAccount;
	}

	public void setDistributorAccount(String distributorAccount) {
		this.distributorAccount = distributorAccount;
	}


	
	
}
