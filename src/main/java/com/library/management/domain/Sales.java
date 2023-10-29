package com.library.management.domain;

import java.util.Date;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Entity
@Table(name = "Sales")
@Data
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long saleId;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@Temporal(TemporalType.TIMESTAMP)
	private Date saleDate;
}
