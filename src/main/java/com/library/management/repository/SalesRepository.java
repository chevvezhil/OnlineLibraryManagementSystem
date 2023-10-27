package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.domain.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long> {

}
