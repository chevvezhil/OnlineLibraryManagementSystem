package com.library.management.storage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.library.management.domain.Book;

public class InMemoryBookStorage {

	private static List<Book> books = new ArrayList<>();
	//Map<sellerId, List<book>>
	
	 @Value("${file.path}")
	 private static String uploadDirectory;

	public static List<Book> getAllBooks() {
		return books;
	}

	public static void addBook(Book book) {
		books.add(book);
		System.out.println("Book has been added successfully");
	}

	public static void removeBook(Book book) {
		books.remove(book);
	}
}
