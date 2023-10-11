package com.library.management.service;

import java.util.List;

import com.library.management.domain.Book;

public interface BooksService {
	
	public void handleBookUpload(Book book);
	public void handleBookDownload(Book book);
	public List<Book> searchBook(String id);
}

