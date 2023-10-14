package com.library.management.search;

public class SearchStrategyFactory {
	
	public static SearchStrategy createSearchStrategy(String criteria) {
		
		if(criteria.equalsIgnoreCase("author"))
			return new AuthorSearchStrategy();
		else if(criteria.equalsIgnoreCase("genre"))
			return new GenreSearchStrategy();
		else if(criteria.equalsIgnoreCase("bookname"))
			return new BookNameSearchStrategy();
		else 
			return new BookNameSearchStrategy();
	}

}
