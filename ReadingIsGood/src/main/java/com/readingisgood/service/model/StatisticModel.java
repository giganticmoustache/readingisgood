package com.readingisgood.service.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StatisticModel {
	private List<String> year;
	private List<String> month;
	private Integer totalOrderCount;
	private Integer totalBookCount;
	private BigDecimal totalPurchasedAmount;
	public StatisticModel(String year, String month, Integer totalOrderCount, Integer totalBookCount,
			BigDecimal totalPurchasedAmount) {
		this.setYear(new ArrayList<>());
		this.getYear().add(year);
		this.setMonth(new ArrayList<>());
		this.getMonth().add(month);
		this.totalOrderCount = totalOrderCount;
		this.totalBookCount = totalBookCount;
		this.totalPurchasedAmount = totalPurchasedAmount;
	}
	
	public List<String> getYear() {
		return year;
	}

	public List<String> getMonth() {
		return month;
	}

	public void setYear(List<String> year) {
		this.year = year;
	}

	public void setMonth(List<String> month) {
		this.month = month;
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
