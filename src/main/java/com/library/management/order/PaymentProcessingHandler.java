package com.library.management.order;

import com.library.management.domain.Order;

public class PaymentProcessingHandler extends OrderHandler {

	public PaymentProcessingHandler(OrderHandler orderHandler) {
		super(orderHandler);
	}

	@Override
	public String processOrder(Order order) {
		
		System.out.println("Inside payment process order handler");
		//Note : Always payment success so moving to next handler
		if (nextHandler != null) {
			return nextHandler.processOrder(order);
		}
		
		return "Something went wrong";
	}

}
