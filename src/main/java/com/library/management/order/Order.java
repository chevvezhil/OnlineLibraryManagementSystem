package com.library.management.order;

import java.util.List;

import com.library.management.domain.Book;
import com.library.management.domain.User;
import com.library.management.utils.OrderStatus;

public class Order {
	
	List<Book> books;
	User user;
	public OrderStatus orderStatus = OrderStatus.PENDING;
	
	
	Order(List<Book> books, User user){
		this.books = books;
		this.user = user;
	}
	
	public List<Book> getBooks(){
		return books;
	}

}
