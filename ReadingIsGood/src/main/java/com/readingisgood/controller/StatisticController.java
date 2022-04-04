package com.readingisgood.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.controller.response.ResponseStatistic;
import com.readingisgood.controller.response.ResponseStatistics;
import com.readingisgood.security.JwtUtils;
import com.readingisgood.service.OrderService;
import com.readingisgood.service.model.StatisticModel;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/getCustomerStatistic")
	public ResponseEntity<ResponseStatistics> getCustomerStatistic() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		List<StatisticModel> models = orderService.getCustomerStatistics(userDetails.getUsername());
		ResponseStatistics body = new ResponseStatistics(models.stream()
				.map(model -> new ResponseStatistic(model.getYear().get(0), model.getMonth().get(0), model.getTotalOrderCount(),
						model.getTotalBookCount(), model.getTotalPurchasedAmount())).collect(Collectors.toList()));
		return new ResponseEntity<ResponseStatistics>(body, HttpStatus.OK);
	}
}
