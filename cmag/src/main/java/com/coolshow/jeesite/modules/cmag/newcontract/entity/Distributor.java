package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;
import com.coolshow.frm.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 
 * 分销商实体类
 *
 */

public class Distributor extends DataEntity<Distributor> {

	private static final long serialVersionUID = 1L;

	private String contract_no; 				// 合同编号
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
	private String old_contract_no;				//旧的合同编号
	private String deposit_bank;				//酷秀开户银行
	private String account_number;				//酷秀开户账号
	private String account_name;				//酷秀开户名称
	private String protocol_type;				//协议类型
	private double advance;						//预付款
	
	public Distributor() {
		super();
	}
	
	@NotNull
	@ExcelField(title = "合同编号",align = 2, sort = 1)
	@Length(min = 1,max = 16)
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	@NotNull
	@ExcelField(title = "分销商编号",align = 2, sort = 2)
	@Length(min = 1,max = 32)
	public String getDistributor_no() {
		return distributor_no;
	}
	public void setDistributor_no(String distributor_no) {
		this.distributor_no = distributor_no;
	}
	@NotNull
	@ExcelField(title = "级别",align = 2, sort = 4)
	@Length(min = 2,max = 10)
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@ExcelField(title = "保证金",align = 2, sort = 2)
	public double getBail() {
		return bail;
	}
	public void setBail(double bail) {
		this.bail = bail;
	}
	
	@ExcelField(title = "年度业务量",align = 2, sort = 4)
	public double getYear_quantity() {
		return year_quantity;
	}
	public void setYear_quantity(double year_quantity) {
		this.year_quantity = year_quantity;
	}
	
	@Length(min = 0,max = 20)
	@ExcelField(title = "结算方式",align = 2, sort = 6)
	public String getSettlement_way() {
		return settlement_way;
	}
	public void setSettlement_way(String settlement_way) {
		this.settlement_way = settlement_way;
	}
	
	@Length(min = 0,max = 22)
	@ExcelField(title = "酷秀银行账户",align = 2, sort = 8)
	public String getCoolshow_account() {
		return coolshow_account;
	}
	public void setCoolshow_account(String coolshow_account) {
		this.coolshow_account = coolshow_account;
	}
	
	@Length(min = 0,max = 15)
	@ExcelField(title = "分销商签约人",align = 2, sort = 10)
	public String getDistributor_contractor() {
		return distributor_contractor;
	}
	public void setDistributor_contractor(String distributor_contractor) {
		this.distributor_contractor = distributor_contractor;
	}
	
	@ExcelField(title = "电话",align = 2, sort = 12)
	@Length(min = 0,max = 13)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@ExcelField(title = "付款方式",align = 2, sort = 14)
	public String getPayment_way() {
		return payment_way;
	}
	public void setPayment_way(String payment_way) {
		this.payment_way = payment_way;
	}
	
	@ExcelField(title = "付款日期",align = 2, sort = 16)
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	
	@ExcelField(title = "对账周期",align = 2, sort = 18)
	public String getChecking_period() {
		return checking_period;
	}
	public void setChecking_period(String checking_period) {
		this.checking_period = checking_period;
	}
	
	@ExcelField(title = "使用系统",align = 2, sort = 20)
	public String getUse_system() {
		return use_system;
	}
	public void setUse_system(String use_system) {
		this.use_system = use_system;
	}
	
	@ExcelField(title = "对账日期",align = 2, sort = 22)
	public String getChecking_date() {
		return checking_date;
	}
	public void setChecking_date(String checking_date) {
		this.checking_date = checking_date;
	}
	
	@ExcelField(title = "开票信息",align = 2, sort = 24)
	public String getBilling_info() {
		return billing_info;
	}
	public void setBilling_info(String billing_info) {
		this.billing_info = billing_info;
	}
	
	@ExcelField(title = "预付款余额/押金退回信息",align = 2, sort = 26)
	public double getPrepaid_balance() {
		return prepaid_balance;
	}
	public void setPrepaid_balance(double prepaid_balance) {
		this.prepaid_balance = prepaid_balance;
	}
	
	@ExcelField(title = "返佣/奖励政策",align = 2, sort = 28)
	public String getRebate() {
		return rebate;
	}
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	
	@ExcelField(title = "所属站点",align = 2, sort = 30)
	public String getSs_site() {
		return ss_site;
	}
	public void setSs_site(String ss_site) {
		this.ss_site = ss_site;
	}
	
	@ExcelField(title = "返佣结算方式",align = 2, sort = 32)
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

	@ExcelField(title = "分销商账号",align = 2, sort = 34)
	public String getDistributor_account() {
		return distributor_account;
	}

	public void setDistributor_account(String distributor_account) {
		this.distributor_account = distributor_account;
	}

	public String getOld_contract_no() {
		return old_contract_no;
	}

	public void setOld_contract_no(String old_contract_no) {
		this.old_contract_no = old_contract_no;
	}

	public String getDeposit_bank() {
		return deposit_bank;
	}

	public void setDeposit_bank(String deposit_bank) {
		this.deposit_bank = deposit_bank;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getProtocol_type() {
		return protocol_type;
	}

	public void setProtocol_type(String protocol_type) {
		this.protocol_type = protocol_type;
	}

	public double getAdvance() {
		return advance;
	}

	public void setAdvance(double advance) {
		this.advance = advance;
	}
}
