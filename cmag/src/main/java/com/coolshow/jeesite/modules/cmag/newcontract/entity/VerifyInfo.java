package com.coolshow.jeesite.modules.cmag.newcontract.entity;


import java.util.Date;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/**
 * @Description 合同初审信息实体类
 * @author 	WQH
 * @Date 2016年6月13日
 */
public class VerifyInfo extends DataEntity<VerifyInfo>{

	private static final long serialVersionUID = 1L;
	
	private String contract_no; 				//合同编号
	private String contract_name; 				//合同名称
	private String info; 						//初审意见信息
	private String verify_person; 				//审核人
	private Date verify_date; 					//审核日期
	private String contract_no_name;			//合同编号名称
	
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	public String getContract_name() {
		return contract_name;
	}
	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getVerify_person() {
		return verify_person;
	}
	public void setVerify_person(String verify_person) {
		this.verify_person = verify_person;
	}
	public Date getVerify_date() {
		return verify_date;
	}
	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}
	public String getContract_no_name() {
		return contract_no_name;
	}
	public void setContract_no_name(String contract_no_name) {
		this.contract_no_name = contract_no_name;
	}
}
