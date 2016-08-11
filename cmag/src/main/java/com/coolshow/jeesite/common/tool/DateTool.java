package com.coolshow.jeesite.common.tool;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.NEW;


/**
 * @Description 日期工具类
 * ClassName: DateTool
 * @author LJW
 * @date 2016年5月10日
 */
public final class DateTool {
	
	private DateTool(){}
	
	static public String getLongDate(Date date){
		SimpleDateFormat  sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tmpString = sDateFormat.format(new Date());
		return tmpString;
	}
	
	static public Date getDateTime(String dateString) throws ParseException{
		DateFormat  simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		return ((DateFormat) simpledateformat).parse(dateString);
	}
	
	/**
	 * 日期转换为指定格式字符串
	 * 
	 * @param ad_Date
	 *            日期
	 * @param as_Format
	 *            格式（"yyyy-MM-dd HH:mm:ss.SSS"）
	 * @return 指定格式字符串
	 */
	static public String uf_DateTimeToString(Date ad_Date, String as_Format) {
		return (new SimpleDateFormat(as_Format)).format(ad_Date);
	}
	
	/**
	 * 日期转换为完整格式字符串（"yyyy-MM-dd HH:mm:ss.SSS"）
	 * 
	 * @param ad_Date
	 *            日期
	 * @return 格式字符串
	 */
	static public String uf_DateTimeToFullString(Date ad_Date) {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(ad_Date);
	}

	/**
	 * 日期转换为长格式字符串（"yyyy-MM-dd HH:mm:ss"）
	 * 
	 * @param ad_Date
	 *            日期
	 * @return 格式字符串
	 */
	static public String uf_DateTimeToLongString(Date ad_Date) {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ad_Date);
	}

	/**
	 * 日期转换为短格式字符串（"yyyy-MM-dd"）
	 * 
	 * @param ad_Date
	 *            日期
	 * @return 格式字符串
	 */
	static public String uf_DateTimeToShortString(Date ad_Date) {
		return (new SimpleDateFormat("yyyy-MM-dd")).format(ad_Date);
	}

	/**
	 * 当前日期转换为指定格式字符串
	 * 
	 * @param as_Format
	 *            格式（"yyyy-MM-dd HH:mm:ss.SSS"）
	 * @return 指定格式字符串
	 */
	static public String uf_NowDateTimeToString(String as_Format) {
		return uf_DateTimeToString(new Date(), as_Format);
	}

	/**
	 * 当前日期转换为完整格式字符串（"yyyy-MM-dd HH:mm:ss.SSS"）
	 * 
	 * @return 格式字符串
	 */
	static public String uf_NowDateTimeToFullString() {
		return uf_DateTimeToString(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 当前日期转换为长格式字符串（"yyyy-MM-dd HH:mm:ss"）
	 * 
	 * @return 格式字符串
	 */
	static public String uf_NowDateTimeToLongString() {
		return uf_DateTimeToString(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 当前日期转换为短格式字符串（"yyyy-MM-dd"）
	 * 
	 * @return 格式字符串
	 */
	static public String uf_NowDateTimeToShortString() {
		return uf_DateTimeToString(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 指定格式字符串转换为日期
	 * 
	 * @param as_Format
	 *            格式（"yyyy-MM-dd HH:mm:ss.SSS"）
	 * @param as_String
	 *            符合格式的字符串
	 * @return 日期对象
	 * @throws Exception
	 *             方法异常
	 */
	static public Date uf_StringToDateTime(String as_Format, String as_String)throws Exception {
		return (new SimpleDateFormat(as_Format)).parse(as_String);
	}

	/**
	 * 完整格式字符串转换为日期（"yyyy-MM-dd HH:mm:ss.SSS"）
	 * 
	 * @param as_String
	 *            符合格式的字符串
	 * @return 日期对象
	 * @throws Exception
	 *             方法异常
	 */
	static public Date uf_FullStringToDateTime(String as_String) throws Exception {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).parse(as_String);
	}

	/**
	 * 长格式字符串转换为日期（"yyyy-MM-dd HH:mm:ss"）
	 * 
	 * @param as_String
	 *            符合格式的字符串
	 * @return 日期对象
	 * @throws Exception
	 *             方法异常
	 */
	static public Date uf_LongStringToDateTime(String as_String) throws Exception {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(as_String);
	}

	/**
	 * 短格式字符串转换为日期（"yyyy-MM-dd"）
	 * 
	 * @param as_String
	 *            符合格式的字符串
	 * @return 日期对象
	 * @throws Exception
	 *             方法异常
	 */
	static public Date uf_ShortStringToDateTime(String as_String) throws Exception {
		return new SimpleDateFormat("yyyy-MM-dd").parse(as_String);
	}
	
	/**
	  * @Description: 将原始日期时间格式转换为  yyyy-MM-dd 格式字符串
	  * @param  @param as_String
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年5月24日
	 */
	static public String uf_getSimpleDateFormat(String as_String){
		@SuppressWarnings("deprecation")
		Date date = new Date(as_String);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(date);
		return dateString;
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param as_date1 
     * @param as_date2 
     * @return 相差天数 
     * @throws ParseException  
     */    
    static public int daysBetween(Date as_date1,Date as_date2) throws ParseException  {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        as_date1=sdf.parse(sdf.format(as_date1));  
        as_date2=sdf.parse(sdf.format(as_date2));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(as_date1);    
        long as_time1 = cal.getTimeInMillis();                 
        cal.setTime(as_date2);    
        long as_time2 = cal.getTimeInMillis();         
        long between_days=(as_time1-as_time2)/(1000*3600*24);  
       return Integer.parseInt(String.valueOf(between_days));           
    }   
}

