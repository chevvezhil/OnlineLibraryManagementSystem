package com.library.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.domain.Admin;
import com.library.management.domain.Order;
import com.library.management.domain.Seller;
import com.library.management.dto.OrderDTO;
import com.library.management.order.OrderDownloadHandler;
import com.library.management.order.OrderHandler;
import com.library.management.order.OrderValidationHandler;
import com.library.management.order.PaymentProcessingHandler;
import com.library.management.service.OrderService;
import com.library.management.service.SalesService;
import com.library.management.utils.OrderStatus;

@RestController
@RequestMapping("/library/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SalesService salesService;

	@PostMapping("/processOrder")
	public ResponseEntity<String> processOrder(@RequestBody OrderDTO orderDto) {
		
		System.out.println("Order is processing ");
		
		Seller seller = new Seller();
		Admin admin = new Admin();

		Order order = new Order();
		order.setBooks(orderDto.getBooks());
		order.setBuyerId(orderDto.getBuyerId());
		order.setModeOfPayment(orderDto.getPaymentMethod());
		order.setOrderStatus(OrderStatus.PENDING);
		
		order.attach(admin);
		order.attach(seller);
		
		order = orderService.createOrder(order);
		
		System.out.println("Order has been confirmed with order ID " + order.getOrderId());
		
		OrderHandler orderProcessingChain = new OrderValidationHandler(
				new PaymentProcessingHandler(new OrderDownloadHandler(null)));

		String zipFiles = orderProcessingChain.processOrder(order);
		
		if(zipFiles !=null) {
			orderService.updateOrderStatus(order.getOrderId(),OrderStatus.SUCCESS);
			salesService.recordSales(order);
		}
		
		System.out.println("Order has been downloaded");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=books.zip");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/zip"))
				.body(zipFiles);

	}
}
