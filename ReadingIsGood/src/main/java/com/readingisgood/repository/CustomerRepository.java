package com.readingisgood.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.readingisgood.entity.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
	Optional<Customer> findByEmail(String email);
	
	Boolean existsByEmail(String email);
}
