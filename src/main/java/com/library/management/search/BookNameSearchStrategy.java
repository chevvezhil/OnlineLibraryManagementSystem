package com.library.management.search;

import java.util.List;
import java.util.stream.Collectors;

import com.library.management.domain.Book;

public class BookNameSearchStrategy implements SearchStrategy{

	@Override
	public List<Book> search(List<Book> books, String keyword) {
		return books.stream().filter(book -> book.getBookname().startsWith(keyword))
				.collect(Collectors.toList());
	}

}
