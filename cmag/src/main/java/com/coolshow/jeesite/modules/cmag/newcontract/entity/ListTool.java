package com.coolshow.jeesite.modules.cmag.newcontract.entity;

import com.coolshow.frm.jeesite.common.persistence.DataEntity;

/**
 * @Description: 列表工具实体类
 * ClassName: TopContacts
 * @author LJW
 * @date 2016年5月6日
 */
public class ListTool extends DataEntity<ListTool>{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String parentId;
	private String parentIds;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
}
