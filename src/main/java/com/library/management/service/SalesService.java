package com.library.management.service;

import java.util.List;

import com.library.management.domain.Order;
import com.library.management.domain.Sales;

public interface SalesService {
	
	public void recordSales(Order order);
	public List<Sales> retriveSalesForSeller(Long sellerId);
}
