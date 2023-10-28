package com.library.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.management.domain.Seller;
import com.library.management.utils.VerificationStatus;

@Service
public interface SellerService {
	 List<Seller> getAllSellers();
	 void updateVerificationStatus(Long sellerId, String verifiedBy);
	 void removeSeller(Long sellerId);
	 void updateSeller(Long sellerId, VerificationStatus status, String verifiedBy, Boolean addedByAdmin);
	 public Seller getSellerByName(String sellerName);
}
