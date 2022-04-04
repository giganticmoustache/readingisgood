package com.readingisgood.service.model;

import java.math.BigDecimal;

public class OrderModel {
	private String customerEmail;
	private String bookName;
	private Integer amount;
	private BigDecimal price;
	public String getCustomerEmail() {
		return customerEmail;
	}
	public String getBookName() {
		return bookName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public OrderModel(String customerEmail, String bookName, Integer amount, BigDecimal price) {
		this.customerEmail = customerEmail;
		this.bookName = bookName;
		this.amount = amount;
		this.price = price;
	}
}
