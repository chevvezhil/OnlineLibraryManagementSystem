package com.library.management.domain;

import com.library.management.utils.VerificationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@Table(name = "Seller")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_id")
	private Long sellerId;
	
	//TO DO:Remove SellerName after DB integration and write join query with UserTable
	@Column(name = "sellerName")
	private String sellerName;
	
	@Column(name = "verificationStatus")
	private VerificationStatus verificationStatus;

	public Seller (String sellerName, Long sellerId, VerificationStatus verificationStatus) {
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.verificationStatus = verificationStatus;
	}
	
	public String getSellerName() {
		return sellerName;
	}
	
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	public Long getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	
	public void setVerificationStatus(VerificationStatus verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	
	public VerificationStatus getVerificationStatus() {
		return verificationStatus;
	}
	
}
