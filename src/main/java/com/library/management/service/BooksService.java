package com.library.management.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.library.management.domain.Book;

public interface BooksService {
	
	public void handleBookUpload(Book book, MultipartFile file);
	public List<Book> searchBook(String criteria, String kwyword);
	public List<Book> getAllBooks();
	List<Book> getBooksBySeller(String sellerName);
	public void updateBookPrice(Long bookId, double price);
	public void deleteBook(Long bookId);
}

