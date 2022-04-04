package com.readingisgood.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.readingisgood.entity.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
	List<Order> findByCustomerIdAndOrderDateBetween(String customerId, Date from, Date to);
	
	Page<Order> findByCustomerId(String customerId, Pageable pageable);
}
