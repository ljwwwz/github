package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/**
 * @Description 扫描件实体
 * ClassName: Scanning.java
 * @author LJW
 * @date 上午8:41:16
 */
public class Scanning extends DataEntity<Scanning>{

	private static final long serialVersionUID = 1L;

	private String contract_no;
	private String enclosure_id;
	
	public Scanning(){
		super();
	}
	
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	public String getEnclosure_id() {
		return enclosure_id;
	}
	public void setEnclosure_id(String enclosure_id) {
		this.enclosure_id = enclosure_id;
	}
	
	
}
