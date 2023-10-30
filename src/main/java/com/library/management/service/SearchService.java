package com.library.management.service;

import java.util.List;

import com.library.management.domain.Book;

public interface SearchService {
	
	public void setSearchStrategy(String criteria);
	public List<Book> searchBooks(String keyword, List<Book> availableBooks);
}
