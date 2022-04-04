package com.readingisgood.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.controller.request.InquiryOrderByDates;
import com.readingisgood.controller.request.InquiryOrderByIdRequest;
import com.readingisgood.controller.request.OrderRequest;
import com.readingisgood.controller.response.ResponseOrder;
import com.readingisgood.controller.response.ResponseOrders;
import com.readingisgood.service.OrderService;
import com.readingisgood.service.model.OrderModel;

@RestController
@RequestMapping("orderServices")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		OrderModel orderModel = new OrderModel(userDetails.getUsername(), request.getBookName(), request.getAmount(), null);
		String id;
		try {
			id = orderService.placeOrder(orderModel);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
		return ResponseEntity.ok("Order placed with id: " + id);
	}
	
	@PostMapping("inquiryOrderById")
	public ResponseEntity<ResponseOrder> inquiryOrderById(@Valid @RequestBody InquiryOrderByIdRequest request) {
		ResponseOrder body = null;
		try {
			OrderModel model = orderService.inquiryOrderById(request.getOrderId());
			body = new ResponseOrder(model.getCustomerEmail(), model.getBookName(), model.getAmount(), model.getPrice());
		} catch (Exception e) {
			new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseOrder>(body, HttpStatus.OK);
	}
	
	@PostMapping("inquiryOrdersByDate")
	public ResponseEntity<ResponseOrders> inquiryOrdersByDate(@Valid @RequestBody InquiryOrderByDates request) {
		ResponseOrders body = new ResponseOrders();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		try {
			List<OrderModel> models = orderService.inquiryOrdersByDate(userDetails.getUsername(), request.getStartDate(), request.getEndDate());
			body.setOrders(models.stream()
					.map(model -> new ResponseOrder(model.getCustomerEmail(), model.getBookName(), model.getAmount(), model.getPrice()))
					.collect(Collectors.toList()));
		} catch (Exception e) {
			new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseOrders>(body, HttpStatus.OK);
	}
}
