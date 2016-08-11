package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.TreeEntity;

/**
 * @Description 分销商基本信息
 * ClassName: DistributorBase.java
 * @author LJW
 * @date 上午8:08:16
 */
public class DistributorBase extends TreeEntity<DistributorBase> {

	/**
	 * serialVersionUID
	 * 上午8:08:41
	 */
	private static final long serialVersionUID = 1L;

	private String distributor_no; 				// 分销商编号
	private String level; 						// 分销商级别
	private double bail;						// 保证金
	private double year_quantity; 				// 年度任务量
	private String settlement_way; 				// 结算方式
	private String coolshow_account; 			// 酷秀银行账号
	private String distributor_contractor; 		// 分销商签约人
	private String phone; 						// 联系电话
	private String payment_way; 				// 付款方式
	private String payment_date;					// 付款日
	private String checking_period;				// 对账周期
	private String use_system;					// 使用系统
	private String checking_date;					// 对账日
	private String billing_info;				// 开票信息
	private double prepaid_balance;				// 预付款余额/押金退回信息
	private String rebate;						// 返佣/奖励政策
	private String ss_site;						// 所属站点
	private String rebate_way;					// 返佣结算方式
	private String create_user;					//创建人
	private String create_time;					//创建时间
	private String modify_user;					//修改人
	private String modify_time;					//修改时间
	private String distributor_account;			//分销商账号
	private String name;						//分销商名称
	private String del_flag;					//生效标识
	
	public DistributorBase() {
		super();
	}
	
	@Override
	public DistributorBase getParent() {
		return parent;
	}

	@Override
	public void setParent(DistributorBase parent) {
		this.parent = parent;
	}

	public String getDistributor_no() {
		return distributor_no;
	}

	public void setDistributor_no(String distributor_no) {
		this.distributor_no = distributor_no;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public double getBail() {
		return bail;
	}

	public void setBail(double bail) {
		this.bail = bail;
	}

	public double getYear_quantity() {
		return year_quantity;
	}

	public void setYear_quantity(double year_quantity) {
		this.year_quantity = year_quantity;
	}

	public String getSettlement_way() {
		return settlement_way;
	}

	public void setSettlement_way(String settlement_way) {
		this.settlement_way = settlement_way;
	}

	public String getCoolshow_account() {
		return coolshow_account;
	}

	public void setCoolshow_account(String coolshow_account) {
		this.coolshow_account = coolshow_account;
	}

	public String getDistributor_contractor() {
		return distributor_contractor;
	}

	public void setDistributor_contractor(String distributor_contractor) {
		this.distributor_contractor = distributor_contractor;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPayment_way() {
		return payment_way;
	}

	public void setPayment_way(String payment_way) {
		this.payment_way = payment_way;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public String getChecking_period() {
		return checking_period;
	}

	public void setChecking_period(String checking_period) {
		this.checking_period = checking_period;
	}

	public String getUse_system() {
		return use_system;
	}

	public void setUse_system(String use_system) {
		this.use_system = use_system;
	}

	public String getChecking_date() {
		return checking_date;
	}

	public void setChecking_date(String checking_date) {
		this.checking_date = checking_date;
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

	public String getDistributor_account() {
		return distributor_account;
	}

	public void setDistributor_account(String distributor_account) {
		this.distributor_account = distributor_account;
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
