package com.library.management.order;

import com.library.management.domain.Order;
import com.library.management.utils.OrderStatus;

public class PaymentProcessingHandler extends OrderHandler {

	public PaymentProcessingHandler(OrderHandler orderHandler) {
		super(orderHandler);
	}

	@Override
	public String processOrder(Order order) {
		
		if (nextHandler != null) {
			order.setOrderStatus(OrderStatus.PAYMENT_SUCCESS);
			return nextHandler.processOrder(order);
		}
		
		order.setOrderStatus(OrderStatus.PAYMENT_FAILURE);
		return "Something went wrong";
	}

}
