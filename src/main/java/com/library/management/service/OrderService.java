package com.library.management.service;

import com.library.management.domain.Order;
import com.library.management.utils.OrderStatus;

public interface OrderService {
	 Order createOrder(Order order);
	 public void updateOrderStatus(Long orderId, OrderStatus orderStatus);
}
