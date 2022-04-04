package com.readingisgood.controller.response;

import java.io.Serializable;
import java.util.List;

public class ResponseOrders implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6731652515264391403L;
	private List<ResponseOrder> orders;

	public List<ResponseOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ResponseOrder> orders) {
		this.orders = orders;
	}
}
