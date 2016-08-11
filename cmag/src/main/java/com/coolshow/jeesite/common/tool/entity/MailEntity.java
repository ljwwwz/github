package com.coolshow.jeesite.common.tool.entity;

import java.io.Serializable;

/**
 * @Description 邮件实体类
 * ClassName: MailEntity.java
 * @author LJW
 * @date 下午6:14:43
 */
public class MailEntity implements Serializable {
	
	private static final long serialVersionUID = 6134088902534068868L;
	
	private String to_user;           		//收件人
	private String from_user;         		//发件人
	private String from_userNmae;     		//发件人邮箱账号
	private String from_userPsw;      		//发件人邮箱密码
	private String subject;			  		//邮件主题
	private String content;			  		//邮件内容
	
	private String contract_name;		    //合同名称
	private String contract_no;				//合同编号
	private String remind_time;				//提醒时间
	
	public MailEntity(){}
	
	public String getTo_user() {
		return to_user;
	}
	public void setTo_user(String to_user) {
		this.to_user = to_user;
	}
	public String getFrom_user() {
		return from_user;
	}
	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	public String getFrom_userNmae() {
		return from_userNmae;
	}
	public void setFrom_userNmae(String from_userNmae) {
		this.from_userNmae = from_userNmae;
	}
	public String getFrom_userPsw() {
		return from_userPsw;
	}
	public void setFrom_userPsw(String from_userPsw) {
		this.from_userPsw = from_userPsw;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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

	public String getRemind_time() {
		return remind_time;
	}

	public void setRemind_time(String remind_time) {
		this.remind_time = remind_time;
	}
}
