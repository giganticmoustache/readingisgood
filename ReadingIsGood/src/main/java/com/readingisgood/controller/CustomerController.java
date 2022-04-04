package com.readingisgood.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.controller.request.LoginRequest;
import com.readingisgood.controller.request.PagingRequest;
import com.readingisgood.controller.request.SignUpRequest;
import com.readingisgood.controller.response.ResponseOrder;
import com.readingisgood.controller.response.ResponseOrders;
import com.readingisgood.entity.Customer;
import com.readingisgood.repository.CustomerRepository;
import com.readingisgood.security.JwtUtils;
import com.readingisgood.service.OrderService;
import com.readingisgood.service.model.OrderModel;

@RestController
@RequestMapping("/customerServices")
public class CustomerController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/operations/signUp")
	public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) {
		if (customerRepository.existsByEmail(request.getEmail())) {
			return ResponseEntity.badRequest().body("A user has already registered with this email!");
		}
		Customer customer = new Customer();
		customer.setEmail(request.getEmail());
		customer.setName(request.getName());
		customer.setPassword(encoder.encode(request.getPassword()));
		customer.setSurname(request.getSurname());
		customerRepository.save(customer);
		return ResponseEntity.ok("Registration is successfull!");
	}
	
	@PostMapping("/operations/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		return ResponseEntity.ok(jwt);
	}
	
	@PostMapping("inquiry/getCustomerOrdersWithPaging")
	public ResponseEntity<ResponseOrders> getCustomerOrdersWithPaging(@Valid @RequestBody PagingRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		ResponseOrders body = new ResponseOrders();
		List<OrderModel> orders = orderService.inquiryCustomerOrdersWithPaging(userDetails.getUsername(),
				request.getPageNum(), request.getPageSize());
		body.setOrders(orders.stream()
					.map(order -> new ResponseOrder(order.getCustomerEmail(), order.getBookName(), order.getAmount(), order.getPrice()))
					.collect(Collectors.toList()));
		return new ResponseEntity<ResponseOrders>(body, HttpStatus.OK);
	}
}
