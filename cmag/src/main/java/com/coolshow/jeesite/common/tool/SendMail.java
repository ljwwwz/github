package com.coolshow.jeesite.common.tool;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.coolshow.jeesite.common.tool.Constants;
import com.coolshow.jeesite.common.tool.entity.MailEntity;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @Description 发送邮件工具类
 * ClassName: SendMail.java
 * @author LJW
 * @date 下午11:06:27
 */
public final class SendMail {
	
	private SendMail(){}
	
	public static void sendMail_test(){
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();  
        // 设定mail server设定smtp邮件服务器
        senderImpl.setHost("smtp.exmail.qq.com");  
        // 建立邮件消息  
        SimpleMailMessage mailMessage = new SimpleMailMessage();  
        // 设置收件人
        mailMessage.setTo("jw_li@coolshow.com.cn");    //收件人
        mailMessage.setFrom("ht_system@coolshow.com.cn");    //发件人
        mailMessage.setSubject(" 测试简单文本邮件发送！");   //邮件标题
        mailMessage.setText(" SPRINGMVC 测试我的简单邮件发送机制！！");   //邮件内容
        senderImpl.setUsername("ht_system@coolshow.com.cn"); // 根据自己的情况,设置username  
        senderImpl.setPassword("Cs12345"); // 根据自己的情况, 设置password  
        Properties prop = new Properties();  
        prop.put("mail.smtp.auth ", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
        prop.put("mail.smtp.timeout ", "25000");  //超时时间
        senderImpl.setJavaMailProperties(prop);  
        // 发送邮件  
        senderImpl.send(mailMessage);  
        System.out.println(" 邮件发送成功.. ");  
    }
	
	 /**
     * @Description: 发送邮件--简单文本
     * @param   
     * @return void  
     * @throws
     * @author LJW
     * @date 2016年7月10日
    */
	public static boolean sendMail(MailEntity mailEntity){
	   	boolean isSuccess = false;
	   	JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
	   	try {
	   		 // 设定mail server设定smtp邮件服务器
	           senderImpl.setHost(Constants.MAIL_SERVER);  
	           // 建立邮件消息  
	           SimpleMailMessage mailMessage = new SimpleMailMessage();  
	           // 设置收件人
	           mailMessage.setTo(mailEntity.getTo_user());    //收件人
	           mailMessage.setFrom(mailEntity.getFrom_user());    //发件人
	           mailMessage.setSubject(mailEntity.getSubject());   //邮件标题
	           mailMessage.setText(mailEntity.getContent());   //邮件内容
	           senderImpl.setUsername(mailEntity.getFrom_userNmae()); // 根据自己的情况,设置username  
	           senderImpl.setPassword(mailEntity.getFrom_userPsw()); // 根据自己的情况, 设置password  
	           Properties prop = new Properties();  
	           prop.put(Constants.MAIL_AUTH, "true"); 	  // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
	           prop.put(Constants.MAIL_TIMEOUT, "25000");  //超时时间
	           senderImpl.setJavaMailProperties(prop);  
	           // 发送邮件  
	           senderImpl.send(mailMessage);  
	           isSuccess = true;
			} catch (Exception e) {
				//e.printStackTrace();
				isSuccess = false;
			}
	   	return isSuccess;
   }
	
	/**发送富文本邮件
	 * @throws MessagingException
	 */
	public static boolean richContentSend(MailEntity mailEntity){
		boolean isSuccess = false;
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	   	try {
	   		 // 设定mail server设定smtp邮件服务器
	   		mailSender.setHost(Constants.MAIL_SERVER);  
	   		MimeMessage msg = mailSender.createMimeMessage();
	   		MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");
	   		// 设置收件人
	   		helper.setTo(mailEntity.getTo_user());    //收件人
	   		helper.setFrom(mailEntity.getFrom_user());    //发件人
	   		helper.setSentDate(new Date());// 发送时间
	   		helper.setSubject(mailEntity.getSubject());   //邮件标题
	   		 //第二个参数true，表示text的内容为html
	   		StringBuffer text = new StringBuffer();
	   		text.append("<body><p>").append(mailEntity.getContent()).append("</p></body>");
	   	    helper.setText(text.toString(),true);
	   	    mailSender.setUsername(mailEntity.getFrom_userNmae()); // 根据自己的情况,设置username  
	   	    mailSender.setPassword(mailEntity.getFrom_userPsw()); // 根据自己的情况, 设置password  
	        Properties prop = new Properties();  
	        prop.put(Constants.MAIL_AUTH, "true"); 	  // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
	        prop.put(Constants.MAIL_TIMEOUT, "25000");  //超时时间
	        mailSender.setJavaMailProperties(prop);  
	   	    mailSender.send(msg);
	   	    isSuccess = true;
	   	} catch (MessagingException e) {
			isSuccess = false;
		}
	   	return isSuccess;
	}
	
	/**
	 * 发送模板邮件
	 */
	public static boolean sendFtlMail(MailEntity mailEntity) {
		boolean isSuccess = false;
		Template template = null;
		Configuration freeMarkerConfig = null;
		HtmlEmail hemail = new HtmlEmail();
		try {
			hemail.setHostName(Constants.MAIL_SERVER);
			hemail.setSmtpPort(Constants.SMTPPORT);
			hemail.setCharset(Constants.CHAR_SET);
			hemail.addTo(mailEntity.getTo_user());
			hemail.setFrom(mailEntity.getFrom_user(), mailEntity.getFrom_userNmae());
			hemail.setAuthentication(mailEntity.getFrom_user(), mailEntity.getFrom_userPsw());
			hemail.setSubject(mailEntity.getSubject());
			freeMarkerConfig = new Configuration();
			freeMarkerConfig.setDirectoryForTemplateLoading(new File(getFilePath()));
			// 获取模板
			template = freeMarkerConfig.getTemplate(getFileName(Constants.TEMPLATEPATH),new Locale("Zh_cn"), "UTF-8");
			// 模板内容转换为string
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", mailEntity.getContent());
			map.put("contractName", mailEntity.getContract_name());
			map.put("contractNo", mailEntity.getContract_no());
			map.put("remindTime", mailEntity.getRemind_time());
			String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
			hemail.setMsg(htmlText);
			hemail.send();
			isSuccess = true;
		} catch (Exception e) {
			//e.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;
	}
	
	/**
	  * @Description: 获取模板文件路径
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年7月19日
	 */
	private static String getFilePath() {
		String path = getAppPath(SendMail.class);
		path = path + File.separator + "mailtemplate" + File.separator;
		path = path.replace("\\", "/");
		return path;
	}
	
	/**
	  * @Description: 获取模板文件名称
	  * @param  @param path
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年7月19日
	 */
	private static String getFileName(String path) {
		path = path.replace("\\", "/");
		return path.substring(path.lastIndexOf("/") + 1);
	}
	
	/**
	  * @Description: 获取模板文件全路径
	  * @param  @param cls
	  * @param  @return 
	  * @return String  
	  * @throws
	  * @author LJW
	  * @date 2016年7月19日
	 */
	public static String getAppPath(Class<?> cls) {
		// 检查用户传入的参数是否为空
		if (cls == null)
			throw new java.lang.IllegalArgumentException("参数不能为空！");
		ClassLoader loader = cls.getClassLoader();
		// 获得类的全名，包括包名
		String clsName = cls.getName() + ".class";
		// 获得传入参数所在的包
		Package pack = cls.getPackage();
		String path = "";
		// 如果不是匿名包，将包名转化为路径
		if (pack != null) {
			String packName = pack.getName();
			// 此处简单判定是否是Java基础类库，防止用户传入JDK内置的类库
			if (packName.startsWith("java.") || packName.startsWith("javax."))
				throw new java.lang.IllegalArgumentException("不要传送系统类！");
			// 在类的名称中，去掉包名的部分，获得类的文件名
			clsName = clsName.substring(packName.length() + 1);
			// 判定包名是否是简单包名，如果是，则直接将包名转换为路径，
			if (packName.indexOf(".") < 0)
				path = packName + "/";
			else {// 否则按照包名的组成部分，将包名转换为路径
				int start = 0, end = 0;
				end = packName.indexOf(".");
				while (end != -1) {
					path = path + packName.substring(start, end) + "/";
					start = end + 1;
					end = packName.indexOf(".", start);
				}
				path = path + packName.substring(start) + "/";
			}
		}
		// 调用ClassLoader的getResource方法，传入包含路径信息的类文件名
		java.net.URL url = loader.getResource(path + clsName);
		// 从URL对象中获取路径信息
		String realPath = url.getPath();
		// 去掉路径信息中的协议名"file:"
		int pos = realPath.indexOf("file:");
		if (pos > -1)
			realPath = realPath.substring(pos + 5);
		// 去掉路径信息最后包含类文件信息的部分，得到类所在的路径
		pos = realPath.indexOf(path + clsName);
		realPath = realPath.substring(0, pos - 1);
		// 如果类文件被打包到JAR等文件中时，去掉对应的JAR等打包文件名
		if (realPath.endsWith("!"))
			realPath = realPath.substring(0, realPath.lastIndexOf("/"));
		/*------------------------------------------------------------ 
		 ClassLoader的getResource方法使用了utf-8对路径信息进行了编码，当路径 
		  中存在中文和空格时，他会对这些字符进行转换，这样，得到的往往不是我们想要 
		  的真实路径，在此，调用了URLDecoder的decode方法进行解码，以便得到原始的 
		  中文及空格路径 
		-------------------------------------------------------------*/
		try {
			realPath = java.net.URLDecoder.decode(realPath, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//System.out.println("realPath----->" + realPath);
		return realPath;
	}
	
	
	public static void main(String[] args) {
		//SendMail.sendMail();
		SendMail.sendMail_test();
	}
}
