package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import java.util.Date;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/***
 * 对查看合同行为记录的实体类
 * @author brj
 *
 */

public class CheckContractLog extends DataEntity<CheckContractLog>{
	private static final long serialVersionUID = 1L;

	private String uuid;   //除主键外的另一唯一标识
	
	private String url;    //所访问url
	
	private String loginName;  //用户登录名
	
	private String name;    //用户真实姓名
	
	private String args;    //用户提交的参数
	
	private String result;  //访问结果
	
	private Date time = new Date();
	
	private String contract_name; 		// 合同名称
	private String contract_no; 		// 合同编号

	public Date getTime() {
		return time;
	}

	
	
	public String getContract_name() {
		return contract_name;
	}

	

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}



	public String getContract_no() {
		return contract_no;
	}



	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}



	public void setTime(Date time) {
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	public CheckContractLog() {
	}



	@Override
	public String toString() {
		return "CheckContractLog [uuid=" + uuid + ", contract_name=" + contract_name + ", contract_no=" + contract_no
				+ ", url=" + url + ", loginName=" + loginName + ", name=" + name + ", args=" + args + ", result="
				+ result + ", time=" + time + "]";
	}



	public CheckContractLog(String uuid, String contract_name, String contract_no, String url, String loginName,
			String name, String args, String result, Date time) {
		this.uuid = uuid;
		this.contract_name = contract_name;
		this.contract_no = contract_no;
		this.url = url;
		this.loginName = loginName;
		this.name = name;
		this.args = args;
		this.result = result;
		this.time = time;
	}

	

	
	
}
