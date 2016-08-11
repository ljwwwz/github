package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import java.io.Serializable;

/**
 * @Description 供前台显示合同提醒列表
 * ClassName: ToolEntity.java
 * @author LJW
 * @date 上午11:56:51
 */
public class ToolEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String warn_id;
	private String menus;
	private String contract_no;
	private String contract_no_name;
	private String contract_name;
	private String contract_type_dm;
	private String enclosure_id;
	private String firstPartyName;
	private String secondPartyName;
	private String qd_date;
	private String remind_state;
	private String remind_time;
	private String remind_name;
	private String remind_content;
	private String remind_contentAll;       //提醒的全部内容
	private String warnPersonFlag;          //前台被提醒人标识
	
	public String getWarn_id() {
		return warn_id;
	}
	public void setWarn_id(String warn_id) {
		this.warn_id = warn_id;
	}
	public String getMenus() {
		return menus;
	}
	public void setMenus(String menus) {
		this.menus = menus;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	public String getContract_no_name() {
		return contract_no_name;
	}
	public void setContract_no_name(String contract_no_name) {
		this.contract_no_name = contract_no_name;
	}
	public String getContract_name() {
		return contract_name;
	}
	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}
	public String getContract_type_dm() {
		return contract_type_dm;
	}
	public void setContract_type_dm(String contract_type_dm) {
		this.contract_type_dm = contract_type_dm;
	}
	public String getEnclosure_id() {
		return enclosure_id;
	}
	public void setEnclosure_id(String enclosure_id) {
		this.enclosure_id = enclosure_id;
	}
	public String getFirstPartyName() {
		return firstPartyName;
	}
	public void setFirstPartyName(String firstPartyName) {
		this.firstPartyName = firstPartyName;
	}
	public String getSecondPartyName() {
		return secondPartyName;
	}
	public void setSecondPartyName(String secondPartyName) {
		this.secondPartyName = secondPartyName;
	}
	public String getQd_date() {
		return qd_date;
	}
	public void setQd_date(String qd_date) {
		this.qd_date = qd_date;
	}
	public String getRemind_state() {
		return remind_state;
	}
	public void setRemind_state(String remind_state) {
		this.remind_state = remind_state;
	}
	public String getRemind_time() {
		return remind_time;
	}
	public void setRemind_time(String remind_time) {
		this.remind_time = remind_time;
	}
	public String getRemind_name() {
		return remind_name;
	}
	public void setRemind_name(String remind_name) {
		this.remind_name = remind_name;
	}
	public String getRemind_content() {
		return remind_content;
	}
	public void setRemind_content(String remind_content) {
		this.remind_content = remind_content;
	}
	public String getRemind_contentAll() {
		return remind_contentAll;
	}
	public void setRemind_contentAll(String remind_contentAll) {
		this.remind_contentAll = remind_contentAll;
	}
	public String getWarnPersonFlag() {
		return warnPersonFlag;
	}
	public void setWarnPersonFlag(String warnPersonFlag) {
		this.warnPersonFlag = warnPersonFlag;
	}
}
