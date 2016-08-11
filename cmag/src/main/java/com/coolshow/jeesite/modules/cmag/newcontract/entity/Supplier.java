package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;
import com.coolshow.frm.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 
 * 供应商实体类
 *
 */
public class Supplier extends DataEntity<Supplier>{

	private static final long serialVersionUID = 1L;
	
	private String contract_no;			//合同编号
	private String supplier_no;			//供应商编号
	private String supplier_type;		//供应商类型
	private String scenicspot_name;		//供应景区名称
	private String receipt_account;		//收款账户
	private String phone;				//联系方式
	private String payment_info;		//付款信息
	private String use_system;			//使用系统
	private double bail;				//保证金
	private String checking_period;		//对账周期
	private String checking_date;		//对账日
	private String payment_date;		//付款日
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
	private String old_contract_no;		//旧的合同编号
	private String supplier_contractor; //供应商签约人
	private String contractor_phone;	//供应商签约人联系电话
	private String supplier_add;		//供应商地址
	private String emergency_level;		//对账紧急程度
	private String confirm_statement;	//是否确认对账单
	private String taxpayer_scale;		//纳税人规模
	private String tariff;				//税率
	private String bail_state;			//押金状态
	private String verify_date;			//返佣核算日期
	private String account_type;		//供应商账户类型
	private String deposit_bank;		//供应商开户银行
	private String account_number;		//供应商开户账号
	private String account_name;		//供应商开户名称
	
	
	public Supplier() {
		super();
	}
	
	@ExcelField(title = "合同编号",align = 2, sort = 2)
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	
	@ExcelField(title = "供应商编号",align = 2, sort = 3)
	public String getSupplier_no() {
		return supplier_no;
	}
	public void setSupplier_no(String supplier_no) {
		this.supplier_no = supplier_no;
	}
	
	@ExcelField(title = "供应商类型",align = 2, sort = 5)
	public String getSupplier_type() {
		return supplier_type;
	}
	public void setSupplier_type(String supplier_type) {
		this.supplier_type = supplier_type;
	}
	
	@NotNull(message="供应景区名称不可为空")
	@ExcelField(title = "供应景区名称",align = 2, sort = 7)
	@Length(min=1,max=100,message="供应景区名称长度必须介于 1 和 100 之间")
	public String getScenicspot_name() {
		return scenicspot_name;
	}
	public void setScenicspot_name(String scenicspot_name) {
		this.scenicspot_name = scenicspot_name;
	}
	
	@ExcelField(title = "收款账号",align = 2, sort = 9)
	@NotNull(message="收款账号不可为空")
	@Length(min=1,max=19,message="收款账号长度必须小于等于19位")
	public String getReceipt_account() {
		return receipt_account;
	}
	public void setReceipt_account(String receipt_account) {
		this.receipt_account = receipt_account;
	}
	
	@ExcelField(title = "联系方式",align = 2, sort = 11)
	@NotNull(message="联系方式不可为空")
	@Length(min=1,max=100,message="联系方式长度必须介于 1 和 100 之间")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@ExcelField(title = "付款信息",align = 2, sort = 13)
	@NotNull(message="付款信息不可为空")
	public String getPayment_info() {
		return payment_info;
	}
	public void setPayment_info(String payment_info) {
		this.payment_info = payment_info;
	}
	
	@ExcelField(title = "使用系统",align = 2, sort = 15)
	public String getUse_system() {
		return use_system;
	}
	public void setUse_system(String use_system) {
		this.use_system = use_system;
	}
	
	@ExcelField(title = "保证金",align = 2, sort = 17)
	@NotNull(message="保证金不可为空")
	public double getBail() {
		return bail;
	}
	public void setBail(double bail) {
		this.bail = bail;
	}
	
	@ExcelField(title = "对账周期",align = 2, sort = 19)
	public String getChecking_period() {
		return checking_period;
	}
	public void setChecking_period(String checking_period) {
		this.checking_period = checking_period;
	}
	
	@ExcelField(title = "对账日",align = 2, sort = 22)
	@NotNull(message="对账日不可为空")
	public String getChecking_date() {
		return checking_date;
	}
	public void setChecking_date(String checking_date) {
		this.checking_date = checking_date;
	}
	
	@ExcelField(title = "付款日",align = 2, sort = 24)
	@NotNull(message="付款日不可为空")
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	
	@ExcelField(title = "开票信息",align = 2, sort = 26)
	@NotNull(message="开票信息不可为空")
	@Length(min=1,max=100,message="开票信息的长度必须介于1与100之间")
	public String getBilling_info() {
		return billing_info;
	}
	public void setBilling_info(String billing_info) {
		this.billing_info = billing_info;
	}
	
	@ExcelField(title = "预付款余额/押金退回信息",align = 2, sort = 28)
	@NotNull(message="预付款余额/押金退回信息不可为空")
	public double getPrepaid_balance() {
		return prepaid_balance;
	}
	public void setPrepaid_balance(double prepaid_balance) {
		this.prepaid_balance = prepaid_balance;
	}
	
	@ExcelField(title = "返佣/奖励政策",align = 2, sort = 30)
	@NotNull(message="返佣/奖励政策不可为空")
	@Length(min=1,max=100,message="返佣/奖励政策的长度必须介于1与100之间")
	public String getRebate() {
		return rebate;
	}
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	
	@NotNull(message="供应商账号不可为空")
	@ExcelField(title = "供应商账号",align = 2, sort = 32)
	@Length(min=1,max=100,message="供应商账号长度必须介于1与100之间")
	public String getSupplier_account() {
		return supplier_account;
	}
	public void setSupplier_account(String supplier_account) {
		this.supplier_account = supplier_account;
	}
	
	@ExcelField(title = "所属站点",align = 2, sort = 34)
	public String getSs_site() {
		return ss_site;
	}
	public void setSs_site(String ss_site) {
		this.ss_site = ss_site;
	}
	@NotNull(message="返佣结算方式不可为空")
	@ExcelField(title = "返佣结算方式",align = 2, sort = 36)
	@Length(min=1,max=100,message="返佣结算方式的长度必须介于1与100之间")
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

	public String getOld_contract_no() {
		return old_contract_no;
	}

	public void setOld_contract_no(String old_contract_no) {
		this.old_contract_no = old_contract_no;
	}

	public String getSupplier_contractor() {
		return supplier_contractor;
	}

	public void setSupplier_contractor(String supplier_contractor) {
		this.supplier_contractor = supplier_contractor;
	}

	public String getContractor_phone() {
		return contractor_phone;
	}

	public void setContractor_phone(String contractor_phone) {
		this.contractor_phone = contractor_phone;
	}

	public String getSupplier_add() {
		return supplier_add;
	}

	public void setSupplier_add(String supplier_add) {
		this.supplier_add = supplier_add;
	}

	public String getEmergency_level() {
		return emergency_level;
	}

	public void setEmergency_level(String emergency_level) {
		this.emergency_level = emergency_level;
	}

	public String getConfirm_statement() {
		return confirm_statement;
	}

	public void setConfirm_statement(String confirm_statement) {
		this.confirm_statement = confirm_statement;
	}

	public String getTaxpayer_scale() {
		return taxpayer_scale;
	}

	public void setTaxpayer_scale(String taxpayer_scale) {
		this.taxpayer_scale = taxpayer_scale;
	}

	public String getTariff() {
		return tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public String getBail_state() {
		return bail_state;
	}

	public void setBail_state(String bail_state) {
		this.bail_state = bail_state;
	}

	public String getVerify_date() {
		return verify_date;
	}

	public void setVerify_date(String verify_date) {
		this.verify_date = verify_date;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
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
	
}
