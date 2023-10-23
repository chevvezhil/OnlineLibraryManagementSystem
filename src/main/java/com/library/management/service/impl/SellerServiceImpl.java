package com.library.management.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.management.domain.Seller;
import com.library.management.repository.SellerRepository;
import com.library.management.service.SellerService;
import com.library.management.utils.VerificationStatus;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerRepository sellerRepository;

	
	@Override
	public List<Seller> getAllSellers() {
		System.out.println("Seller Exits for Admin page");
		var response = sellerRepository.findAll();
		return response;
	}
	
	@Override
	public String updateVerificationStatus(Long sellerId) {
		var response =  sellerRepository.updateVerificationStatus(VerificationStatus.VERIFIED, sellerId);
		return response!=0 ? "Failed to updated" : "Verification Status updated successfully";
	}

}
