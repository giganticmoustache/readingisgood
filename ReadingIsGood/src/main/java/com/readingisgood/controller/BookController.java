package com.readingisgood.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.controller.request.BookRequest;
import com.readingisgood.service.BookService;
import com.readingisgood.service.model.BookModel;

@RestController
@RequestMapping("/bookServices")
public class BookController {

	@Autowired
	BookService bookService;
	
	@PostMapping("/saveBook")
	public ResponseEntity<?> saveBook(@Valid @RequestBody BookRequest request) {
		BookModel model = new BookModel();
		model.setAmount(request.getAmount());
		model.setName(request.getName());
		model.setPrice(request.getPrice());
		bookService.save(model);
		return ResponseEntity.ok("Book saved successfully!");
	}
}
