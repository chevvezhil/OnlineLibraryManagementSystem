package com.library.management.domain;

public class Book {
	
	private String bookId;
	
	private String bookname;
	
	private String author;
	
	private String genre;
	
	private String pdfUrl;
	
	private int quandity;
	
	private double price;
	
	private String Seller; // Book has a seller (Considering only one seller is uploading this book)
	
	public Book(String name, String author, String genre, double price) {
		this.bookname = name;
		this.author = author;
		this.genre = genre;
		this.price = price;
	}
	
	public String getBookId() {
		return bookId;
	}
	
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	
	public String getSeller() {
		return Seller;
	}

	public void setSeller(String seller) {
		Seller = seller;
	}


	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getQuandity() {
		return quandity;
	}

	public void setQuandity(int quandity) {
		this.quandity = quandity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

}
