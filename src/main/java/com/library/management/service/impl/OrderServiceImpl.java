package com.library.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.domain.Order;
import com.library.management.repository.OrderRepository;
import com.library.management.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order createOrder(Order order) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String booksJson = objectMapper.writeValueAsString(order.getBooks());
			order.setItems(booksJson);
		} catch (JsonProcessingException e) {
			System.out.println(e);
		}

		return orderRepository.save(order);
	}

}
