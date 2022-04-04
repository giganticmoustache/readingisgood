package com.readingisgood.controller.request;

import java.io.Serializable;

import javax.validation.constraints.Min;

public class PagingRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1377138827445004279L;
	@Min(value = 0, message = "Page number can not be negative number!")
	private Integer pageNum;
	@Min(value = 1, message = "Page size can not be less than 1!")
	private Integer pageSize;
	public Integer getPageNum() {
		return pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
