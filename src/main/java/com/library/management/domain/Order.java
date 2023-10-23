package com.library.management.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.library.management.utils.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ORDERS")
@NoArgsConstructor
public class Order {

	
	@Transient
	List<Book> books;

    @Column(columnDefinition = "LONGTEXT")
	private String items;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@CreationTimestamp
	@Column(name = "purchased_at", updatable = false)
	private Date purchased_time;
	
	@Column(name="buyer_id")
	private String buyerId;
	
    @Enumerated(EnumType.STRING)
	@Column(name ="order_status")
	public OrderStatus orderStatus;
	

}
