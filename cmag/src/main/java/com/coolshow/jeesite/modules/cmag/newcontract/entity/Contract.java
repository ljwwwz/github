package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;
import com.coolshow.frm.jeesite.common.utils.excel.annotation.ExcelField;
import com.coolshow.frm.jeesite.modules.sys.entity.Area;
import com.coolshow.frm.jeesite.modules.sys.entity.Office;
import com.coolshow.frm.jeesite.modules.sys.entity.User;
import com.coolshow.jeesite.modules.cmag.conWarn.entity.ContractWarn;
import com.google.common.collect.Lists;

/**
 * @Description 合同基本信息实体 ClassName: Contract
 * @author LJW
 * @date 2016年5月10日
 */
public class Contract extends DataEntity<Contract> {

	private static final long serialVersionUID = 1L;

	private String contract_name; 				// 合同名称
	private String contract_no; 				// 合同编号
	private String firstParty; 					// 甲方id
	private String firstPartyName; 				// 甲方名称
	private String secondParty; 				// 乙方
	private String secondPartyName; 			// 乙方名称
	private String begin_date; 					// 开始日期
	private String end_date; 					// 结束日期
	private String qdr; 						// 签订人id
	private String qdrName; 					// 签订人名称
	private String qd_date; 					// 签订日期
	private String enclosure_id; 				// 附件id
	private String ss_area; 					// 所属区域
	private String create_user; 				// 创建人id
	private String create_time; 				// 创建日期
	private String modify_user; 				// 修改人
	private String modify_time; 				// 修改日期
	private String remarks; 					// 备注
	private String state_id; 					// 合同状态id
	private String contract_type_dm; 			// 合同类型代码
	private String user_id; 					// 审核人id
	private String fj; 							// 临时保存上传的附件
	private String scanningFj; 					// 临时保存扫描件
	private String old_contract_no;				// 旧合同信息
	private String area_id;						// 区域id
	private String custodian;					//合同保管人
	private String receipt_time;				//签收时间
	private String original_file;				//原件
	private String copies;						//份数
	private String original_code;				//原编码
	private String scan_version;				//扫描版
	private String remind_state;				//提醒状态
	private String createUser_name;				//创建人名称
	private String menus;
	private String contract_no_name;			//合同编号名称(前台展示)
	private String mail_address;				//邮寄地址
	private String street_info;                 //街道信息
	private String history_conNo;				//历史的合同编号
	
	private User lxgUser;
	private User qdrUser;
	private String fileType;					//文件类型标识位
	private String isUpScan;					//是否上传扫描件标识位

	private User draftStaff; 					// 合同拟定人
	private User followStaff; 					// 常用联系人

	private RelationContract relationcontract;
	private Office jfOffice;                     //表示页面上的甲方
	private Office yfOffice;					 //表示页面上的乙方
	
	private Supplier supplier; 					// 供应商信息
	private Distributor distributor; 			// 分销商信息
	private Other other;						// 其他信息
	private ConFollow conFollow; 				// 合同跟踪人信息
	private ContractType contractType; 			// 合同类型信息
	private Enclosure enclosure; 				// 附件信息
	private ContractState contractState; 		// 合同状态信息
	private Scanning scanning; 					// 扫描件信息
	private VerifyInfo verifyInfo;				//初审信息
	private DistributorBase distributorBase;	//分销商基本信息
	private SupplierBase supplierBase;			//供应商基本信息

	private Area area;
	
	private ContractWarn conWarn;
	private List<ContractWarn> contractWarns = Lists.newArrayList();    //一个合同有多条提醒内容     
	
	public Contract() {
		super();
	}

	public Contract(String id) {
		super(id);
	}

	@ExcelField(title = "合同名称", type = 0, align = 2, sort = 2)
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

	@ExcelField(title = "甲方", type = 1, align = 2, sort = 10)
	public String getFirstParty() {
		return firstParty;
	}

	public void setFirstParty(String firstParty) {
		this.firstParty = firstParty;
	}

	@ExcelField(title = "乙方",type = 1, align = 1, sort = 15)
	public String getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(String secondParty) {
		this.secondParty = secondParty;
	}

	@ExcelField(title = "开始日期", type = 0, align = 2, sort = 3)
	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	@ExcelField(title = "结束日期", type = 0, align = 1, sort = 4)
	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getQdr() {
		return qdr;
	}

	public void setQdr(String qdr) {
		this.qdr = qdr;
	}

	@ExcelField(title = "签订日期", type = 0, align = 1, sort = 5)
	public String getQd_date() {
		return qd_date;
	}

	public void setQd_date(String qd_date) {
		this.qd_date = qd_date;
	}

	public String getEnclosure_id() {
		return enclosure_id;
	}

	public void setEnclosure_id(String enclosure_id) {
		this.enclosure_id = enclosure_id;
	}

	@ExcelField(title = "所属区域", type = 0, align = 2,sort = 29)
	public String getSs_area() {
		return ss_area;
	}

	
	
	public String getStreet_info() {
		return street_info;
	}

	public void setStreet_info(String street_info) {
		this.street_info = street_info;
	}

	public void setSs_area(String ss_area) {
		this.ss_area = ss_area;
	}

	@ExcelField(title = "合同拟定人", type = 2, align = 2,sort = 11)
	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	@ExcelField(title = "合同创建日期", type = 1, align = 1, sort = 6)
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	@ExcelField(title = "合同修改人", type = 1, sort = 9)
	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	@ExcelField(title = "合同修改日期", type = 1, align = 1, sort = 7)
	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	@ExcelField(title = "备注", type = 0, align = 1, sort = 30)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	@ExcelField(title = "合同类型代码:01:供应商 02:分销商  03:其他", type = 0, align = 2, sort = 8)
	public String getContract_type_dm() {
		return contract_type_dm;
	}

	public void setContract_type_dm(String contract_type_dm) {
		this.contract_type_dm = contract_type_dm;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFj() {
		return fj;
	}

	public void setFj(String fj) {
		this.fj = fj;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public ConFollow getConFollow() {
		return conFollow;
	}

	public void setConFollow(ConFollow conFollow) {
		this.conFollow = conFollow;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public Enclosure getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(Enclosure enclosure) {
		this.enclosure = enclosure;
	}

	public ContractState getContractState() {
		return contractState;
	}

	public void setContractState(ContractState contractState) {
		this.contractState = contractState;
	}

	public User getLxgUser() {
		return lxgUser;
	}

	public void setLxgUser(User lxgUser) {
		this.lxgUser = lxgUser;
	}

	public User getQdrUser() {
		return qdrUser;
	}

	public void setQdrUser(User qdrUser) {
		this.qdrUser = qdrUser;
	}

	@ExcelField(title = "甲方名称", align = 2, sort = 11)
	public String getFirstPartyName() {
		return firstPartyName;
	}
	
	public void setFirstPartyName(String firstPartyName) {
		this.firstPartyName = firstPartyName;
	}

	@ExcelField(title = "乙方名称",align = 2, sort = 12)
	public String getSecondPartyName() {
		return secondPartyName;
	}

	public void setSecondPartyName(String secondPartyName) {
		this.secondPartyName = secondPartyName;
	}

	@ExcelField(title = "合同签订人名称",align = 2, sort = 17)
	public String getQdrName() {
		return qdrName;
	}

	public void setQdrName(String qdrName) {
		this.qdrName = qdrName;
	}

	public String getScanningFj() {
		return scanningFj;
	}

	public void setScanningFj(String scanningFj) {
		this.scanningFj = scanningFj;
	}

	public Scanning getScanning() {
		return scanning;
	}

	public void setScanning(Scanning scanning) {
		this.scanning = scanning;
	}

	public User getDraftStaff() {
		return draftStaff;
	}

	public void setDraftStaff(User draftStaff) {
		this.draftStaff = draftStaff;
	}

	public User getFollowStaff() {
		return followStaff;
	}

	public void setFollowStaff(User followStaff) {
		this.followStaff = followStaff;
	}

	public Office getJfOffice() {
		return jfOffice;
	}

	public void setJfOffice(Office jfOffice) {
		this.jfOffice = jfOffice;
	}

	public Office getYfOffice() {
		return yfOffice;
	}

	public void setYfOffice(Office yfOffice) {
		this.yfOffice = yfOffice;
	}

	public RelationContract getRelationcontract() {
		return relationcontract;
	}

	public void setRelationcontract(RelationContract relationcontract) {
		this.relationcontract = relationcontract;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getIsUpScan() {
		return isUpScan;
	}

	public void setIsUpScan(String isUpScan) {
		this.isUpScan = isUpScan;
	}

	public Other getOther() {
		return other;
	}

	public void setOther(Other other) {
		this.other = other;
	}

	public String getOld_contract_no() {
		return old_contract_no;
	}

	public void setOld_contract_no(String old_contract_no) {
		this.old_contract_no = old_contract_no;
	}

	public VerifyInfo getVerifyInfo() {
		return verifyInfo;
	}

	public void setVerifyInfo(VerifyInfo verifyInfo) {
		this.verifyInfo = verifyInfo;
	}

	public DistributorBase getDistributorBase() {
		return distributorBase;
	}

	public void setDistributorBase(DistributorBase distributorBase) {
		this.distributorBase = distributorBase;
	}

	public SupplierBase getSupplierBase() {
		return supplierBase;
	}

	public void setSupplierBase(SupplierBase supplierBase) {
		this.supplierBase = supplierBase;
	}
	
	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getCustodian() {
		return custodian;
	}

	public void setCustodian(String custodian) {
		this.custodian = custodian;
	}

	public String getOriginal_file() {
		return original_file;
	}

	public void setOriginal_file(String original_file) {
		this.original_file = original_file;
	}

	public String getCopies() {
		return copies;
	}

	public void setCopies(String copies) {
		this.copies = copies;
	}

	public String getOriginal_code() {
		return original_code;
	}

	public void setOriginal_code(String original_code) {
		this.original_code = original_code;
	}

	public String getScan_version() {
		return scan_version;
	}

	public void setScan_version(String scan_version) {
		this.scan_version = scan_version;
	}

	public String getReceipt_time() {
		return receipt_time;
	}

	public void setReceipt_time(String receipt_time) {
		this.receipt_time = receipt_time;
	}
	
	public List<ContractWarn> getContractWarns() {
		return contractWarns;
	}

	public void setContractWarns(List<ContractWarn> contractWarn) {
		this.contractWarns = contractWarn;
	}
	public ContractWarn getConWarn() {
		return conWarn;
	}

	public void setConWarn(ContractWarn conWarn) {
		this.conWarn = conWarn;
	}

	public String getRemind_state() {
		return remind_state;
	}

	public void setRemind_state(String remind_state) {
		this.remind_state = remind_state;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}

	@ExcelField(title = "合同编号", type = 0, align = 1, sort = 1)
	public String getContract_no_name() {
		return contract_no_name;
	}

	public void setContract_no_name(String contract_no_name) {
		this.contract_no_name = contract_no_name;
	}

	public String getCreateUser_name() {
		return createUser_name;
	}

	public void setCreateUser_name(String createUser_name) {
		this.createUser_name = createUser_name;
	}

	public String getMail_address() {
		return mail_address;
	}

	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}

	public String getHistory_conNo() {
		return history_conNo;
	}

	public void setHistory_conNo(String history_conNo) {
		this.history_conNo = history_conNo;
	}
}
