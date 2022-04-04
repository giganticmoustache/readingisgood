package com.readingisgood.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
	@Id
    private String id;
	@Indexed
	private String customerId;
	private String bookId;
	private Integer amount;
	private BigDecimal total;
	private Date orderDate;
	public String getId() {
		return id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getBookId() {
		return bookId;
	}
	public Integer getAmount() {
		return amount;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}
