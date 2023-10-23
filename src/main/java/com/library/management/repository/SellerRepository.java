package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.domain.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

}
