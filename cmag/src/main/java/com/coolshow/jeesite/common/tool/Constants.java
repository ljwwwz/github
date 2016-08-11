package com.coolshow.jeesite.common.tool;

import java.io.Serializable;

/**
 * @Description 常量工具类
 * ClassName: Constants.java
 * @author LJW
 * @date 下午5:28:01
 */
public final class Constants implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Constants(){}
	
	public static final  String MAIL_SERVER 		= "smtp.exmail.qq.com"; 			//邮件服务器地址
	public static final  String MAIL_AUTH 			= "mail.smtp.auth";					//让服务器进行认证,认证用户名和密码是否正确  
	public static final  String MAIL_TIMEOUT 		= "mail.smtp.timeout";				//超时时间设置
	
	public static final String CHAR_SET 			= "utf-8";
	
	public static final String TEMPLATEPATH 		= "mailtemplate/conwarn.ftl";		//模板文件路径
	public static final int SMTPPORT				= 25;								//发送邮件服务器端口
}
