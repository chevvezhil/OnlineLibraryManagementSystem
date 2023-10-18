package com.library.management.order;

import java.util.Map;
import java.util.stream.Collectors;

import com.library.management.domain.Book;
import com.library.management.service.BooksService;

public class OrderDownloadHandler extends OrderHandler {

	private BooksService bookService;

	public OrderDownloadHandler(OrderHandler orderHandler) {
		super(orderHandler);
	}

	@Override
	public String processOrder(Order order) {

		Map<String, String> result = order.getBooks().stream()
				.collect(Collectors.toMap(Book::getBookId, bookService::handleBookDownload));
		return result.toString();
	}
}
