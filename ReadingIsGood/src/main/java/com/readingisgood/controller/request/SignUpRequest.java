package com.readingisgood.controller.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class SignUpRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4655858530203571144L;
	@NotNull
	private String email;
	@NotNull
    private String password;
	@NotNull
    private String name;
	@NotNull
    private String surname;
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
