package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import java.util.Date;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/**
 * @Description 附件信息
 * ClassName: Enclosure
 * @author LJW
 * @date 2016年5月10日
 */
public class Enclosure extends DataEntity<Enclosure>{

	private static final long serialVersionUID = 1L;
	
	private String enclosure_id;
	private String path;                	//附件路径
	private String name;                	//附件名称
	private String create_user;        		 //创建人
	private Date upload_time;           	//上传时间
	private String flag;					//01：上传的附件  02：上传的扫描件
	
	public Enclosure(){
		super();
	}
	
	public String getEnclosure_id() {
		return enclosure_id;
	}
	public void setEnclosure_id(String enclosure_id) {
		this.enclosure_id = enclosure_id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public Date getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
