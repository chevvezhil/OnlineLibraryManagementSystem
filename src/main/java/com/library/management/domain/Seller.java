package com.library.management.domain;

import com.library.management.order.Observer;
import com.library.management.utils.VerificationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Seller implements Observer{
	
	@Id
	@Column(name = "sellerId")
	private Long sellerId;
	
	@Column(name = "sellerName")
	private String sellerName;
	
	@Column(name = "verificationStatus")
	@Enumerated(EnumType.STRING)
	private VerificationStatus verificationStatus;
	
	@Column(name = "verifiedBy")
	private String verifiedBy;
	
	@Column(name = "addedByAdmin", columnDefinition="boolean default false")
	private Boolean addedByAdmin;

	public Seller (Long sellerId, String sellerName, VerificationStatus verificationStatus) {
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.verificationStatus = verificationStatus;
	}

	@Override
	public void update(Order order) {
		for(Book book : order.getBooks()) {
			System.out.println("Book Name " + book.getBookname() + " Seller :" + book.getSeller() + " Order Status " + order.getOrderStatus());
		}
	}
}
