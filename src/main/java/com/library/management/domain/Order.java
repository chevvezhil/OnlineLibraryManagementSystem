package com.library.management.domain;

import java.util.List;

import com.library.management.utils.OrderStatus;

public class Order {

	List<Book> books;
	
	private String user;
	
	public OrderStatus orderStatus = OrderStatus.PENDING;
	
	Order(List<Book> books, String user){
		this.books = books;
		this.user = user;
	}
	
	public List<Book> getBooks(){
		return books;
	}

	public String getUser() {
		return user;
	}
}
