package com.library.management.dto;

import java.util.List;

import com.library.management.domain.Book;

import lombok.Data;

@Data
public class OrderDTO {
	private List<Book> books;
	private String buyerId;
	private String paymentMethod;
}
