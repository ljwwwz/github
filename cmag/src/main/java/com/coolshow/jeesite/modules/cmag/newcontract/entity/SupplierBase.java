package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.TreeEntity;

/**
 * @Description 供应商基本信息
 * ClassName: SupplierBase.java
 * @author LJW
 * @date 上午8:08:55
 */
public class SupplierBase extends TreeEntity<SupplierBase> {

	/**
	 * serialVersionUID
	 * 上午8:08:49
	 */
	private static final long serialVersionUID = 1L;
	
	private String supplier_no;			//供应商编号
	private String supplier_type;		//供应商类型
	private String scenicspot_name;		//供应景区名称
	private String receipt_account;		//收款账户
	private String phone;				//联系方式
	private String payment_info;		//付款信息
	private String use_system;			//使用系统
	private double bail;				//保证金
	private String checking_period;		//对账周期
	private String checking_date;			//对账日
	private String payment_date;			//付款日
	private String billing_info;		//开票信息
	private double prepaid_balance;		//预付款余额/押金退回信息
	private String rebate;				//返佣/奖励政策
	private String supplier_account;	//供应商账号
	private String ss_site;				//所属站点
	private String rebate_way;			//返佣结算方式
	private String create_user;			//创建人
	private String create_time;			//创建时间
	private String modify_user;			//修改人
	private String modify_time;			//修改时间
	private String name;				//供应商名称
	private String del_flag;			//生效标识
	

	public SupplierBase() {
		super();
	}
	
	public String getSupplier_no() {
		return supplier_no;
	}

	public void setSupplier_no(String supplier_no) {
		this.supplier_no = supplier_no;
	}

	public String getSupplier_type() {
		return supplier_type;
	}

	public void setSupplier_type(String supplier_type) {
		this.supplier_type = supplier_type;
	}

	public String getScenicspot_name() {
		return scenicspot_name;
	}

	public void setScenicspot_name(String scenicspot_name) {
		this.scenicspot_name = scenicspot_name;
	}

	public String getReceipt_account() {
		return receipt_account;
	}

	public void setReceipt_account(String receipt_account) {
		this.receipt_account = receipt_account;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPayment_info() {
		return payment_info;
	}

	public void setPayment_info(String payment_info) {
		this.payment_info = payment_info;
	}

	public String getUse_system() {
		return use_system;
	}

	public void setUse_system(String use_system) {
		this.use_system = use_system;
	}

	public double getBail() {
		return bail;
	}

	public void setBail(double bail) {
		this.bail = bail;
	}

	public String getChecking_period() {
		return checking_period;
	}

	public void setChecking_period(String checking_period) {
		this.checking_period = checking_period;
	}

	public String getChecking_date() {
		return checking_date;
	}

	public void setChecking_date(String checking_date) {
		this.checking_date = checking_date;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public String getBilling_info() {
		return billing_info;
	}

	public void setBilling_info(String billing_info) {
		this.billing_info = billing_info;
	}

	public double getPrepaid_balance() {
		return prepaid_balance;
	}

	public void setPrepaid_balance(double prepaid_balance) {
		this.prepaid_balance = prepaid_balance;
	}

	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getSupplier_account() {
		return supplier_account;
	}

	public void setSupplier_account(String supplier_account) {
		this.supplier_account = supplier_account;
	}

	public String getSs_site() {
		return ss_site;
	}

	public void setSs_site(String ss_site) {
		this.ss_site = ss_site;
	}

	public String getRebate_way() {
		return rebate_way;
	}

	public void setRebate_way(String rebate_way) {
		this.rebate_way = rebate_way;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	@Override
	public void setParent(SupplierBase parent) {
		this.parent = parent;
		
	}

	@Override
	public SupplierBase getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	
}
