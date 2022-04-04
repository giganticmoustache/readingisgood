package com.readingisgood.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.readingisgood.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {
	Optional<Book> findByName(String name);
}
