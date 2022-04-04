package com.readingisgood.controller.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BookRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8654164962566862793L;
	@NotNull
	private String name;
	@NotNull
	@Min(value = 0, message = "Book amount cannot be less than 0")
	@Digits(fraction = 0, integer = 16, message = "Book amount can only be integer numbers!")
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
