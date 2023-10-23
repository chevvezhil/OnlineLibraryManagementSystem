package com.library.management.domain;

import com.library.management.utils.VerificationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seller")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sellerId")
	private Long sellerId;
	
	//TO DO:Remove SellerName after DB integration and write join query with UserTable
	@Column(name = "sellerName")
	private String sellerName;
	
	@Column(name = "verificationStatus")
	@Enumerated(EnumType.STRING)
	private VerificationStatus verificationStatus;

	public Seller (String sellerName, Long sellerId, VerificationStatus verificationStatus) {
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.verificationStatus = verificationStatus;
	}	
}
