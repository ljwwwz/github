package com.coolshow.jeesite.common.tool;

/**
 * @Description: 合同类型代码枚举类
 * ClassName: ContractTypeEnum
 * @author LJW
 * @date 2016年5月6日
 */
public enum ContractTypeEnum {
	
	SUPPLIER("供应商","01"),DISTRIBUTOR("分销商","02"),QT("其他","03");
	
	private String name;
	private String index;
	
	private ContractTypeEnum(String name,String index){
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
		for (ContractTypeEnum cTypeEnum:ContractTypeEnum.values()) {
			if(cTypeEnum.getIndex().equals(index)){
				return cTypeEnum.getName();
			}
		}
		return null;
	}
}
