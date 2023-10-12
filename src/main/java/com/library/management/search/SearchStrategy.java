package com.library.management.search;

import java.util.List;

import com.library.management.domain.Book;

public interface SearchStrategy {
	
    List<Book> search(List<Book> books, String keyword);

}
