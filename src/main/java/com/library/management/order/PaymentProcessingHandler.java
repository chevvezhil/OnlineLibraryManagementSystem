package com.library.management.order;

public class PaymentProcessingHandler extends OrderHandler {

	public PaymentProcessingHandler(OrderHandler orderHandler) {
		super(orderHandler);
	}

	@Override
	public String processOrder(Order order) {
		
		//Note : Always payment success so moving to next handler
		if (nextHandler != null) {
			return nextHandler.processOrder(order);
		}
		
		return "Something went wrong";
	}

}
