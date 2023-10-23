package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
