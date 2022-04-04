package com.readingisgood.controller.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class LoginRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2010482019400615891L;
	@NotNull
	private String email;
	@NotNull
    private String password;
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
