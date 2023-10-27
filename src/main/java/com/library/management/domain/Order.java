package com.library.management.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.library.management.order.Observer;
import com.library.management.utils.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ORDERS")
@NoArgsConstructor
public class Order {
	
	@ManyToMany
	@JoinTable(name = "order_books", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> books;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@CreationTimestamp
	@Column(name = "purchased_at", updatable = false)
	private Date purchased_time;

	@Column(name = "buyer_id")
	private String buyerId;

	@Enumerated(EnumType.STRING)
	@Column(name = "order_status")
	public OrderStatus orderStatus;

	@Transient
	private List<Observer> observers = new ArrayList<>();

	public void setOrderStatus(OrderStatus status) {
		this.orderStatus = status;
		if (status.equals(OrderStatus.SUCCESS))
			notifyObservers();
	}

	public void attach(Observer observer) {
		observers.add(observer);
	}

	public void detach(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
}
