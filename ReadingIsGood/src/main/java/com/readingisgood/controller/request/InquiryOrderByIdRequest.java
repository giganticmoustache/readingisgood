package com.readingisgood.controller.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class InquiryOrderByIdRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 465646284234951639L;
	@NotNull
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
