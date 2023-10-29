package com.library.management.order;

import java.util.List;

import com.library.management.domain.Book;
import com.library.management.domain.Order;
import com.library.management.utils.OrderStatus;

public class OrderValidationHandler extends OrderHandler {
	
	
	public OrderValidationHandler(OrderHandler orderHandler) {
		
		super(orderHandler);
	}

	@Override
	public String processOrder(Order order) {
		
		List<Book> books = order.getBooks();
		boolean isNull = books.stream().anyMatch(book -> book.getBookId() == null);

		if (isNull) {
			order.setOrderStatus(OrderStatus.FAILED);
			return "Book Id shouldn't be null";
		}else {
			if (nextHandler != null) {
				order.setOrderStatus(OrderStatus.ORDER_VALIDATED);
				return nextHandler.processOrder(order);
			}
		}
		
		return "Something Went Wrong";
	}
}
