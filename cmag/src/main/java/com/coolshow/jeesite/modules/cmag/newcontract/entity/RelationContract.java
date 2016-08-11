package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/**
 * @Description 关联合同信息实体
 * ClassName: RelationContract.java
 * @author LJW
 * @date 上午11:03:01
 */
public class RelationContract extends DataEntity<RelationContract> {
	
	private static final long serialVersionUID = 1L;
	private String contract_no;
	private String relation_no;
	private String name;
	private String contract_no_name;
	
	public RelationContract(){
		super();
	}
	
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	public String getRelation_no() {
		return relation_no;
	}
	public void setRelation_no(String relation_no) {
		this.relation_no = relation_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContract_no_name() {
		return contract_no_name;
	}

	public void setContract_no_name(String contract_no_name) {
		this.contract_no_name = contract_no_name;
	}
	
}
