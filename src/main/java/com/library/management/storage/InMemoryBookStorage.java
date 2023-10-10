package com.library.management.storage;

import java.util.ArrayList;
import java.util.List;

import com.library.management.domain.Book;

public class InMemoryBookStorage {

	private static List<Book> books = new ArrayList<>();

	public static List<Book> getAllBooks() {
		return books;
	}

	public static void addBook(Book book) {
		books.add(book);
	}

	public static void removeBook(Book book) {
		books.remove(book);
	}
}
