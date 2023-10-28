package com.library.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.library.management.domain.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long> {
	 
	 @Query("SELECT s FROM Sales s WHERE s.seller.id = :sellerId")
	  List<Sales> findSalesBySellerId(@Param("sellerId") Long sellerId);
}
