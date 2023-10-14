package com.library.management.search;

import java.util.List;
import java.util.stream.Collectors;

import com.library.management.domain.Book;

public class GenreSearchStrategy implements SearchStrategy{

	@Override
	public List<Book> search(List<Book> books, String keyword) {
		
		//TODO: Change to contains
			return books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(keyword))
					.collect(Collectors.toList());
	}

}
