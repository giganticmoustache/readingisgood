package com.readingisgood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.readingisgood.entity.Book;
import com.readingisgood.repository.BookRepository;
import com.readingisgood.service.model.BookModel;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	@Transactional
	public void save(BookModel book) {
		Book entity = null;
		try {
			entity = bookRepository.findByName(book.getName()).get();
		} catch (Exception e) {
		}
		if (entity == null) {
			entity = new Book();
			entity.setName(book.getName());
			entity.setRemaining(book.getAmount());
			entity.setAmount(book.getPrice());
		} else {
			entity.setRemaining(entity.getRemaining() + book.getAmount());
		}
		bookRepository.save(entity);
	}
}
