package com.readingisgood.entity;

import java.math.BigDecimal;

public class Statistic {
	private String year;
	private String month;
	private Integer totalOrderCount;
	private Integer totalBookCount;
	private BigDecimal totalPurchasedAmount;
	public String getYear() {
		return year;
	}
	public String getMonth() {
		return month;
	}
	public Integer getTotalOrderCount() {
		return totalOrderCount;
	}
	public Integer getTotalBookCount() {
		return totalBookCount;
	}
	public BigDecimal getTotalPurchasedAmount() {
		return totalPurchasedAmount;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setTotalOrderCount(Integer totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}
	public void setTotalBookCount(Integer totalBookCount) {
		this.totalBookCount = totalBookCount;
	}
	public void setTotalPurchasedAmount(BigDecimal totalPurchasedAmount) {
		this.totalPurchasedAmount = totalPurchasedAmount;
	}
	
}
