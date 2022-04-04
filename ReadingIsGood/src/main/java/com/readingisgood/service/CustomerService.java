package com.readingisgood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.readingisgood.entity.Customer;
import com.readingisgood.repository.CustomerRepository;
import com.readingisgood.service.model.CustomerModel;

@Service
public class CustomerService implements UserDetailsService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password!"));
		return CustomerModel.build(customer);
	}

}
