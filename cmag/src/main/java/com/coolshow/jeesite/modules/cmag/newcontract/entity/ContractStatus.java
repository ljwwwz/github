package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

public class ContractStatus extends DataEntity<ContractStatus>{

	private static final long serialVersionUID = 1L;
	private String statusDm;
	private String statusName;
	
	
	public ContractStatus() {
		super();
	}
	public String getStatusDm() {
		return statusDm;
	}
	public void setStatusDm(String statusDm) {
		this.statusDm = statusDm;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
