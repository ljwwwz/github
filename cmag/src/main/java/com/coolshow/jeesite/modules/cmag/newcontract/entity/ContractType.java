package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

public class ContractType extends DataEntity<ContractType>{

	private static final long serialVersionUID = 1L;
	private String typeDm;
	private String typeName;
	
	public ContractType() {
		super();
	}
	public String getTypeDm() {
		return typeDm;
	}
	public void setTypeDm(String typeDm) {
		this.typeDm = typeDm;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
