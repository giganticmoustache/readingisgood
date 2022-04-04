package com.readingisgood.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {
	
	@Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Long remaining;
    private BigDecimal amount;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Long getRemaining() {
		return remaining;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRemaining(Long remaining) {
		this.remaining = remaining;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
