package com.library.management.order;

public abstract class OrderHandler {
	
	protected OrderHandler nextHandler;
	
	public OrderHandler(OrderHandler orderHandler) {
		this.nextHandler = orderHandler;
	}
	
	public abstract String  processOrder(Order order);
}
