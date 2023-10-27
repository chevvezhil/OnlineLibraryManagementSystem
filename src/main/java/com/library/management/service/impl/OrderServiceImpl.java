package com.library.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.domain.Order;
import com.library.management.repository.OrderRepository;
import com.library.management.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	

	@Override
	public Order createOrder(Order order) {
		
		System.out.println("Inside create Order");
		
		 Order createdOrder = orderRepository.save(order);
		 
		 System.out.println("Order saved");
	    return createdOrder;
	}

}
