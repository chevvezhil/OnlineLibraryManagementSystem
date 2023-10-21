package com.library.management.order;

import com.library.management.domain.Order;

public abstract class OrderHandler {
	
	protected OrderHandler nextHandler;
	
	public OrderHandler(OrderHandler orderHandler) {
		this.nextHandler = orderHandler;
	}
	
	public abstract String  processOrder(Order order);
}
