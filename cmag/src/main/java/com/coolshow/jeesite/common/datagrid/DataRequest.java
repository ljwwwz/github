package com.coolshow.jeesite.common.datagrid;

import java.io.Serializable;

public class DataRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	// 当前页码
	private int page;
	// 页面可显示行数
	private int rows;
	// 用于排序的列名
	private String sidx;
	// 排序的方式desc/asc
	private String sord;
	// 是否是搜索请求
	private boolean search;
	// 已经发送的请求的次数
	private String nd;

	private String searchField;
	private String searchOper;
	private String searchString;
	private String filters;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchOper() {
		return searchOper;
	}

	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}
}
