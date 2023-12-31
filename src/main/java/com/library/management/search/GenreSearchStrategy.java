package com.library.management.search;

import java.util.List;
import java.util.stream.Collectors;

import com.library.management.domain.Book;

public class GenreSearchStrategy implements SearchStrategy{

	@Override
	public List<Book> search(List<Book> books, String keyword) {
		
			return books.stream().filter(book -> book.getGenre().toLowerCase().contains(keyword.toLowerCase()))
					.collect(Collectors.toList());
	}

}
