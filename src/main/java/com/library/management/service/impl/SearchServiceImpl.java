package com.library.management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.management.domain.Book;
import com.library.management.search.SearchStrategy;
import com.library.management.search.SearchStrategyFactory;
import com.library.management.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{
	
	private SearchStrategy searchStrategy;
	
	public void setSearchStrategy(String criteria) {
		searchStrategy = SearchStrategyFactory.createSearchStrategy(criteria);
	}

	public List<Book> searchBooks(String keyword, List<Book> availableBooks){
		return searchStrategy.search(availableBooks, keyword);
	}

}
