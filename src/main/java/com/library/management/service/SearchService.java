package com.library.management.service;

import java.util.List;

import com.library.management.domain.Book;
import com.library.management.search.SearchStrategy;
import com.library.management.search.SearchStrategyFactory;

public class SearchService {
	
	private SearchStrategy searchStrategy;
	
	public void setSearchStrategy(String criteria) {
		searchStrategy = SearchStrategyFactory.createSearchStrategy(criteria);
	}

	public List<Book> searchBooks(String keyword, List<Book> availableBooks){
		return searchStrategy.search(availableBooks, keyword);
	}
}
