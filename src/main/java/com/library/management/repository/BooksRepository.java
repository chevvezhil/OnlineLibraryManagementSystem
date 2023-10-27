package com.library.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.domain.Book;

public interface BooksRepository extends JpaRepository<Book, String> {
	List<Book> findBySeller(String seller);
}
