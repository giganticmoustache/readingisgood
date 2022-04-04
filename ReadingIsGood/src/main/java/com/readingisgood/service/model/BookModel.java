package com.readingisgood.service.model;

import java.math.BigDecimal;

public class BookModel {
	private String name;
	private Long amount;
	private BigDecimal price;
	public String getName() {
		return name;
	}
	public Long getAmount() {
		return amount;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
