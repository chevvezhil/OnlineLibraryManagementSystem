package com.library.management.service;

import java.util.List;

import com.library.management.domain.Book;
import com.library.management.search.SearchStrategy;
import com.library.management.search.SearchStrategyFactory;
import com.library.management.storage.InMemoryBookStorage;

public class SearchService {
	
	private SearchStrategy searchStrategy;
	
	public void setSearchStrategy(String criteria) {
		searchStrategy = SearchStrategyFactory.createSearchStrategy(criteria);
	}

	public List<Book> searchBooks(String keyword){
		List<Book> availableBooks = InMemoryBookStorage.getAllBooks();
		return searchStrategy.search(availableBooks, keyword);
	}
}
