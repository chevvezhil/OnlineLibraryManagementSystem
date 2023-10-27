package com.library.management.domain;

import com.library.management.order.Observer;

public class Admin  implements Observer{

	@Override
	public void update(Order order) {
		for(Book book : order.getBooks()) {
			System.out.println("Book Name " + book.getBookname() + " Seller :" + book.getSeller() + " Order Status " + order.getOrderStatus());
		}

	}

}
