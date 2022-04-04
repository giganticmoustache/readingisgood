package com.readingisgood.controller.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4979244118384181715L;
	@NotNull
	private String bookName;
	@NotNull
	@Min(value = 1, message = "Order amount cannot be less than 1!")
	private Integer amount;
	public String getBookName() {
		return bookName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
