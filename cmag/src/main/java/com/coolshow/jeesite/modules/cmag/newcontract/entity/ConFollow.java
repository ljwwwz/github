package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/**
 * @Description 合同履行跟踪人实体
 * ClassName: ConFollow
 * @author LJW
 * @date 2016年5月12日
 */
public class ConFollow extends DataEntity<ConFollow>{

	private static final long serialVersionUID = 1L;
	private String contract_no;       //合同编号
	private String user_id;		     //跟踪人员id
	private String createuser_id;    //合同拟定人id
	
    public ConFollow(){
    	super();
    }

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCreateuser_id() {
		return createuser_id;
	}

	public void setCreateuser_id(String createuser_id) {
		this.createuser_id = createuser_id;
	}	
}
