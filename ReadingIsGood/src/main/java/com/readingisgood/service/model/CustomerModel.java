package com.readingisgood.service.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.readingisgood.entity.Customer;

public class CustomerModel implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6897263850325999499L;
	private String id;
	private String username;
	@JsonIgnore
	private String password;
    private String name;
    private String surname;
    private List<GrantedAuthority> authorities;
    
    public CustomerModel(String id, String username, String password, String name, String surname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority customer = new SimpleGrantedAuthority("CUSTOMER");
		authorities.add(customer);
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public static CustomerModel build(Customer customer) {
		return new CustomerModel(customer.getId(), customer.getEmail(), customer.getPassword(), customer.getName(), customer.getSurname());
	}
}
