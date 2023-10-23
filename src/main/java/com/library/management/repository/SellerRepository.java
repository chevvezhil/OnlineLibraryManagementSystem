package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.library.management.domain.Seller;
import com.library.management.utils.VerificationStatus;

public interface SellerRepository extends JpaRepository<Seller, Long> {
	@Modifying
	@Query(value = "update seller s set s.verificationStatus = ?1 where s.sellerId = ?2", nativeQuery = true)
	int updateVerificationStatus(VerificationStatus status, Long id);
}
