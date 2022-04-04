package com.readingisgood.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.readingisgood.entity.Book;
import com.readingisgood.entity.Customer;
import com.readingisgood.entity.Order;
import com.readingisgood.entity.Statistic;
import com.readingisgood.repository.BookRepository;
import com.readingisgood.repository.CustomerRepository;
import com.readingisgood.repository.OrderRepository;
import com.readingisgood.service.model.OrderModel;
import com.readingisgood.service.model.StatisticModel;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Transactional
	public String placeOrder(OrderModel orderModel) throws Exception {
		Book book = bookRepository.findByName(orderModel.getBookName())
				.orElseThrow(() -> new Exception("No book found with name: " + orderModel.getBookName()));
		if (book.getRemaining().compareTo(orderModel.getAmount().longValue()) < 0) {
			throw new Exception("Not enough books left, please change amount of " + orderModel.getBookName());
		}
		Customer customer = customerRepository.findByEmail(orderModel.getCustomerEmail()).get();
		Order entity = new Order();
		entity.setAmount(orderModel.getAmount());
		entity.setBookId(book.getId());
		entity.setCustomerId(customer.getId());
		entity.setOrderDate(Calendar.getInstance().getTime());
		entity.setTotal(book.getAmount().multiply(BigDecimal.valueOf(orderModel.getAmount().longValue())));
		book.setRemaining(book.getRemaining() - orderModel.getAmount());
		bookRepository.save(book);
		return orderRepository.save(entity).getId();
	}
	
	@Transactional
	public OrderModel inquiryOrderById(String id) throws Exception {
		Order entity = orderRepository.findById(id)
				.orElseThrow(() -> new Exception("No order found!"));
		Book book = bookRepository.findById(entity.getBookId()).get();
		Customer customer = customerRepository.findById(entity.getCustomerId()).get();
		return new OrderModel(customer.getEmail(), book.getName(), entity.getAmount(), entity.getTotal());
	}
	
	@Transactional
	public List<OrderModel> inquiryOrdersByDate(String email, Date start, Date end) {
		Customer customer = customerRepository.findByEmail(email).get();
		List<Order> orders = orderRepository.findByCustomerIdAndOrderDateBetween(customer.getId(), start, end);
		return orders.stream()
				.map(order -> new OrderModel(order.getCustomerId(), order.getBookId(), order.getAmount(), order.getTotal()))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public List<OrderModel> inquiryCustomerOrdersWithPaging(String email, Integer pageNum, Integer pageSize) {
		Customer customer = customerRepository.findByEmail(email).get();
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("orderDate").descending());
		Page<Order> orders = orderRepository.findByCustomerId(customer.getId(), pageable);
		return orders.getContent().stream()
		.map(order -> new OrderModel(order.getCustomerId(), order.getBookId(), order.getAmount(), order.getTotal()))
		.collect(Collectors.toList());
	}
	
	@Transactional
	public List<StatisticModel> getCustomerStatistics(String email) {
		Customer customer = customerRepository.findByEmail(email).get();
		// criteria
        Criteria criteria = Criteria.where("customerId").is(customer.getId());
        MatchOperation matchOperation = Aggregation.match(criteria);
        // Query field
        ProjectionOperation projectionOperation = Aggregation.project("totalBookCount", "totalPurchasedAmount", "amount", "total")
                .and(DateOperators.Month.monthOf("orderDate")).as("month")
                .and(DateOperators.Year.yearOf("orderDate")).as("year");
        // Grouping statistics
        GroupOperation groupOperation = Aggregation.group("year","month")
                .addToSet("year").as("year")
                .addToSet("month").as("month")
                .count().as("totalOrderCount")
                .sum("amount").as("totalBookCount")
                .sum("total").as("totalPurchasedAmount");
        // sort
        SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "_id.year", "_id.month"));
        // Query results
        AggregationResults<Statistic> results = mongoTemplate.aggregate(Aggregation
        		.newAggregation(Order.class, matchOperation, projectionOperation, groupOperation, sortOperation)
        		, Statistic.class);
        return results.getMappedResults().stream()
        		.map(result -> new StatisticModel(result.getYear(), result.getMonth(), result.getTotalOrderCount(), result.getTotalBookCount(),
        				result.getTotalPurchasedAmount())).collect(Collectors.toList());
	}
}
