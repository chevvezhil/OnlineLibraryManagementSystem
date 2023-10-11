package com.library.management.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

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
	
	@Column(name = "isVerified")
	private boolean isVerified;
	
	public Seller (String sellerName, Long sellerId, boolean isVerified) {
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.isVerified = isVerified;
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
	
	public void setIsVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	
	public boolean getIsVerified() {
		return isVerified;
	}
	
}
