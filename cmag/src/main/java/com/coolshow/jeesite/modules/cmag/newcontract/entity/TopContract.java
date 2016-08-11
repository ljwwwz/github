package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/**
 * 合同常用跟踪人实体类
 * @author WQH
 *
 */
public class TopContract extends DataEntity<TopContract> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String user_id;
	private String second_id;
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSecond_id() {
		return second_id;
	}
	public void setSecond_id(String second_id) {
		this.second_id = second_id;
	}
	
}
