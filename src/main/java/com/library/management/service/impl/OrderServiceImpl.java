package com.library.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.domain.Order;
import com.library.management.repository.OrderRepository;
import com.library.management.service.OrderService;
import com.library.management.utils.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order createOrder(Order order) {
		 return orderRepository.save(order);
	}
	
	@Override
	public void updateOrderStatus(Long orderId, OrderStatus orderStatus) {
		Order orderInfo = orderRepository.getReferenceById(orderId);
		orderInfo.setOrderStatus(orderStatus);
		orderRepository.save(orderInfo);
	}
	
	

}
