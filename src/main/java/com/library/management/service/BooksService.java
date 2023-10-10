package com.library.management.service;

import com.library.management.domain.Book;

public interface BooksService {
	
	public void handleBookUpload(Book book);
	public void handleBookDownload(Book book);
}
