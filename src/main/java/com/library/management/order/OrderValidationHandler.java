package com.library.management.order;

import com.library.management.domain.Book;
import java.util.List;

public class OrderValidationHandler extends OrderHandler {

	public OrderValidationHandler(OrderHandler orderHandler) {
		super(orderHandler);
	}

	@Override
	public String processOrder(Order order) {

		List<Book> books = order.getBooks();
		boolean isNull = books.stream().anyMatch(book -> book.getBookId() == null);

		if (isNull)
			return "Book Id shouldn't be null";
		else {
			if (nextHandler != null) {
				return nextHandler.processOrder(order);
			}
		}
		
		return "Something Went Wrong";
	}
}
