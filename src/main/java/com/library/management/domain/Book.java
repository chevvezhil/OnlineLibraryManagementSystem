package com.library.management.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Book_Master")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;

	private String bookname;

	private String author;

	private String genre;

	private String pdfUrl;

	private int quandity;

	private double price;

	private String seller; // Book has a seller (Considering only one seller is uploading this book)

	@ManyToMany(mappedBy = "books")
	@JsonIgnoreProperties("books")
    private Set<Order> orders = new HashSet<>();
	
	@CreationTimestamp
	@Column(name = "created_time", updatable = false)
	private Date bookCreationTime;

	public Book(String name, String author, String genre, double price, String seller) {
		this.bookname = name;
		this.author = author;
		this.genre = genre;
		this.price = price;
		this.seller = seller;
	}

}
