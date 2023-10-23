 package com.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.domain.Seller;
import com.library.management.service.SellerService;
import com.library.management.storage.InMemoryAuthentication;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private  SellerService sellerService;
	
	InMemoryAuthentication auth = new InMemoryAuthentication();
    
	@GetMapping("/sellerDetails")
    @ResponseBody
    public ResponseEntity<List<Seller>> getSellerDetails() {
		List<Seller> response = sellerService.getAllSellers();
    	return ResponseEntity.ok(response);
    }
	
	@PostMapping("/updateVerificationStatus")
	@ResponseBody
	public ResponseEntity<String> updateVerificationStatus(@RequestBody Seller seller) {
		String response = sellerService.updateVerificationStatus(seller.getSellerId());
    	return ResponseEntity.ok(response);
    }
	
}
