package com.library.management.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.domain.Book;
import com.library.management.domain.Order;
import com.library.management.domain.Sales;
import com.library.management.domain.Seller;
import com.library.management.repository.SalesRepository;
import com.library.management.service.SalesService;
import com.library.management.service.SellerService;

@Service
public class SalesServiceImpl implements SalesService{

	@Autowired
    private SalesRepository salesRepository;
	
	@Autowired
	private SellerService sellerService;
	
	public void recordSales(Order order) {
		 
		List<Sales> sales = order.getBooks().stream().map(book -> createSalesForBook(order,book)).collect(Collectors.toList());
		salesRepository.saveAll(sales);
		 
	}
	
	private Sales createSalesForBook(Order order, Book book) {
		
		Sales sale = new Sales();
		
		Seller seller = sellerService.getSellerByName(book.getSeller());
		sale.setSeller(seller);
		sale.setOrder(order);
		sale.setBook(book);
		sale.setSaleDate(new Date());
		
		return sale;
	}
	
	public List<Sales> retriveSalesForSeller(Long sellerId){
		return salesRepository.findSalesBySellerId(sellerId);
	}
}
