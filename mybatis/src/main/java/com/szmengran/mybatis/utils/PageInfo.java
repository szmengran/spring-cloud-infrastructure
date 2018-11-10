package com.szmengran.mybatis.utils;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pageSize;
	private Integer pageNo;
	private Integer total;
	private List<T> list;
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
