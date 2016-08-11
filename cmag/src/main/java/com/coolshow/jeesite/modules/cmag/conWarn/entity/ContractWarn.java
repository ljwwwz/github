package com.coolshow.jeesite.modules.cmag.conWarn.entity;


import java.util.Date;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/**
 * @Description 合同提醒实体
 * @author LJW
 * @date 2016年7月5日
 */
public class ContractWarn extends DataEntity<ContractWarn> {

	private static final long serialVersionUID = 1L;

	private String warn_id;
	private String warn_contractNo;         //合同编号 
	private String remind_time;				//提醒时间
	private String remind_content;			//提醒内容
	private String remind_person;			//提醒人id
	private String remind_name;				//提醒人名称
	private String remind_persons;			//列表显示提醒人
	private String name;
	private String mail_state;				//邮件发送状态 01:未发送邮件 02:已发送邮件
	
	public ContractWarn() {
		super();
	}

	public ContractWarn(String id) {
		super(id);
	}
	
	public String getWarn_id() {
		return warn_id;
	}

	public void setWarn_id(String warn_id) {
		this.warn_id = warn_id;
	}
	public String getRemind_content() {
		return remind_content;
	}
	public void setRemind_content(String remind_content) {
		this.remind_content = remind_content;
	}
	public String getRemind_person() {
		return remind_person;
	}
	public void setRemind_person(String remind_person) {
		this.remind_person = remind_person;
	}
	public String getRemind_name() {
		return remind_name;
	}
	public void setRemind_name(String remind_name) {
		this.remind_name = remind_name;
	}
	public String getRemind_persons() {
		return remind_persons;
	}
	public void setRemind_persons(String remind_persons) {
		this.remind_persons = remind_persons;
	}

	public String getWarn_contractNo() {
		return warn_contractNo;
	}

	public void setWarn_contractNo(String warn_contractNo) {
		this.warn_contractNo = warn_contractNo;
	}

	public String getRemind_time() {
		return remind_time;
	}

	public void setRemind_time(String remind_time) {
		this.remind_time = remind_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail_state() {
		return mail_state;
	}

	public void setMail_state(String mail_state) {
		this.mail_state = mail_state;
	}
}
