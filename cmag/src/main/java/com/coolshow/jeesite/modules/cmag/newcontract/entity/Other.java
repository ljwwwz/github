package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/**
 * 其他合同信息实体
 * @author WQH
 * @date 2016年6月3日
 */
public class Other extends DataEntity<Other> {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String code;
	private String contract_no;
	private String old_contract_no;
	
	public Other(){
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getOld_contract_no() {
		return old_contract_no;
	}

	public void setOld_contract_no(String old_contract_no) {
		this.old_contract_no = old_contract_no;
	}
}
