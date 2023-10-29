package com.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.domain.Sales;
import com.library.management.domain.Seller;
import com.library.management.domain.User;
import com.library.management.domain.UserSellerRequest;
import com.library.management.service.SalesService;
import com.library.management.service.SellerService;
import com.library.management.service.UserService;
import com.library.management.utils.VerificationStatus;

@RestController
@RequestMapping("/library/seller")
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private SalesService salesService;

	@GetMapping("/sellerDetails")
	@ResponseBody
	public ResponseEntity<List<Seller>> getSellerDetails() {
		List<Seller> response = sellerService.getAllSellers();
		System.out.println("Retrived All the sellers");
		return ResponseEntity.ok(response);
	}

	@PostMapping("/updateVerificationStatus")
	@ResponseBody
	public ResponseEntity<?> updateVerificationStatus(@RequestBody Seller seller) {
		sellerService.updateVerificationStatus(seller.getSellerId(), seller.getVerifiedBy());
		System.out.println("Updated seller verification status");
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/removeSeller")
	@ResponseBody
	public ResponseEntity<?> removeSeller(@RequestBody Seller seller) {
		sellerService.removeSeller(seller.getSellerId());
		userService.removeUser(seller.getSellerId());
		
		System.out.println("Seller has been removed successfully");
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/addSeller")
	@ResponseBody
	public ResponseEntity<?> addSellerAndUser(@RequestBody UserSellerRequest request) {

		User user = request.getUser();
		Seller seller = request.getSeller();

		userService.registerUser(user);
		sellerService.updateSeller(user.getUserId(), VerificationStatus.VERIFIED, seller.getVerifiedBy(),
				seller.getAddedByAdmin());
		System.out.println("Seller has been added successfully");

		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/sales/{sellerName}")
	public ResponseEntity<?> getSalesInfo(@PathVariable String sellerName) {

		Long sellerId = sellerService.getSellerByName(sellerName).getSellerId();
		List<Sales> sales = salesService.retriveSalesForSeller(sellerId);
		
		System.out.println("Saled information retrived");


		if (sales.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(sales, HttpStatus.OK);
		}
	}
}
