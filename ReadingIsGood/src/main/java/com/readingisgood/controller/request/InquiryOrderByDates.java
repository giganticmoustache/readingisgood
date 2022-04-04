package com.readingisgood.controller.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class InquiryOrderByDates implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8699379108503742984L;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
