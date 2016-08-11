package com.coolshow.jeesite.common.tool;
import java.util.Calendar;
import java.util.UUID;

/**
 * @Description 唯一键生成工具类
 * ClassName: UuidFactory.java
 * @author LJW
 * @date 上午9:16:34
 */
public class UuidFactory {
	
	/**
	  * @Description: 主键生成策略
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月7日
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	  * @Description: 生成0 - 100 之间的随机整数
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月7日
	 */
	public static String getRanNum() {
		return String.valueOf((int)(Math.random()*100));
	}
	
	/**
	  * @Description: 获取当前年份
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月15日
	 */
	public static String getNowYear() {
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		//a.get(Calendar.MONTH)+1  a.get(Calendar.DATE)
		return year;
	}
	
	/**
	  * @Description: 获取合同编号
	  * @param  @param conName             合同主体名称
	  * @param  @param contract_type       合同类型代码
	  * @param  @param area_jc             区域简称
	  * @param  @param contractNo          查询到的最大的合同编号
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年6月27日
	 */
	public static String getContractNo(String conName,String contract_type,String area_jc,String contractNo){
		StringBuffer contract_no = new StringBuffer(conName);
		contract_no.append("-").append(getNowYear()).append("-");
		if (ContractTypeEnum.SUPPLIER.getIndex().equals(contract_type)) {
			contract_no.append("购");
		}
		if (ContractTypeEnum.DISTRIBUTOR.getIndex().equals(contract_type)) {
			contract_no.append("销");
		}
		contract_no.append("-").append(area_jc).append("-第");
		if (contractNo == null) {
			contractNo = "";
		}
		int length_no = contractNo.length();
		String tmp = "";
		if (length_no == 1) {
			tmp = String.valueOf(Integer.valueOf(contractNo)+1);
			contract_no.append("000").append(tmp);
		}
		if (length_no == 2) {
			tmp = String.valueOf(Integer.valueOf(contractNo)+1);
			contract_no.append("00").append(tmp);
		}
		if (length_no == 3) {
			tmp = String.valueOf(Integer.valueOf(contractNo)+1);
			contract_no.append("0").append(tmp);
		}
		if (length_no == 4) {
			tmp = String.valueOf(Integer.valueOf(contractNo)+1);
			contract_no.append(tmp);
		}
		if ( length_no == 0 ){
			contract_no.append("0001");
		}
		contract_no.append("号");
		return contract_no.toString(); 
	}
}
