package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/**
 * @Description 合同状态代码
 * ClassName: ContractState
 * @author LJW
 * @date 2016年5月11日
 */
public class ContractState extends DataEntity<ContractState>{

	private static final long serialVersionUID = 1L;
	
	
	private String state_dm;
	private String state_name;
	private String next;
	private String prev;
	
	public ContractState() {
		super();
	}
	
	public String getState_dm() {
		return state_dm;
	}
	public void setState_dm(String state_dm) {
		this.state_dm = state_dm;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "ContractState [state_dm=" + state_dm + ", state_name=" + state_name + ", next=" + next + ", prev="
				+ prev + "]";
	}

	public ContractState(String state_dm, String state_name, String next, String prev) {
		this.state_dm = state_dm;
		this.state_name = state_name;
		this.next = next;
		this.prev = prev;
	}
	
}
