package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/**
 * @Description 合同主体关联中间表
 * ClassName: CustomerRelation.java
 * @author LJW
 * @date 下午5:09:41
 */
public class CustomerRelation extends DataEntity<CustomerRelation>{
	
	private static final long serialVersionUID = 1L;
	
	private String contract_no;        //要关联的合同编号
	private String qdf_no;			   //合同签定方编号 分别关联：供销商编号；分销商编号；组织结构编号
	private String flag;			   //合同主体标识 包括：甲:01 乙:02 丙:03丁:04 戊:05己:06庚:07辛:08壬:09癸:10  目前就十个
	private String qdf_flag;		   //签定方标识：0：酷秀 2:供应商 1:分销商
	
	public CustomerRelation() {
		super();
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getQdf_no() {
		return qdf_no;
	}

	public void setQdf_no(String qdf_no) {
		this.qdf_no = qdf_no;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getQdf_flag() {
		return qdf_flag;
	}

	public void setQdf_flag(String qdf_flag) {
		this.qdf_flag = qdf_flag;
	}
	
}
