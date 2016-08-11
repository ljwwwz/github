package com.coolshow.jeesite.common.tool;

/**
 * @Description 合同状态代码枚举类
 * ClassName: ContractStateEnum.java
 * @author LJW
 * @date 上午10:06:25
 */
public enum ContractStateEnum {
	
	NEW("待发送","01"),PRAEIUDICIUM("待初审","02"),SHORT_LIST("初审通过","03"),PRAEIUDICIUM_FAILURE("初审不通过","04"),
	OA_PASS("OA审批通过","05"),SCANS_CONFIRM("扫描件待确认","06"),SCANS_FAILURE("扫描件确认不通过","07"),CONTRACT_EXECUTION("合同生效","08"),COPY("复制合同","09");
	
	private String name;
	private String index;
	
	private ContractStateEnum(String name,String index){
		this.name = name;
		this.index = index;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	
	public static String  getTypeName(String index){
		for (ContractStateEnum cTypeEnum:ContractStateEnum.values()) {
			if(cTypeEnum.getIndex().equals(index)){
				return cTypeEnum.getName();
			}
		}
		return null;
	}
}
