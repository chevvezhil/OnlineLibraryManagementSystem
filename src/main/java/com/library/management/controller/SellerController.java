package com.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.library.management.domain.Book;
import com.library.management.domain.Sales;
import com.library.management.domain.Seller;
import com.library.management.service.BooksService;
import com.library.management.service.SalesService;
import com.library.management.service.SellerService;

@RestController
@RequestMapping("/api/seller")
public class SellerController {
	
	@Autowired
	private BooksService bookService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SalesService salesService;
	
	@PostMapping("/uploadBook")
	@ResponseBody
	public ResponseEntity<?> handleFileUpload(@RequestPart("pdfFile") MultipartFile file,
			@RequestParam("bookname") String bookName, @RequestParam("author") String author,
			@RequestParam("genre") String genre, @RequestParam("price") double price,
			@RequestParam("seller") String sellerName) {

		System.out.println("book data " + bookName + " " + author + " " + genre + " " + price + "seller " + sellerName);

		Book book = new Book(bookName, author, genre, price, sellerName);
		bookService.handleBookUpload(book, file);

		System.out.println("Book uploaded");
		return ResponseEntity.status(HttpStatus.OK).build();

	}
	
	@GetMapping("/books/{sellerName}")
    public ResponseEntity<List<Book>> getBooksBySeller(@PathVariable String sellerName) {
        List<Book> books = bookService.getBooksBySeller(sellerName);

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }
	
	@PostMapping("/updateBookPrice")
	@ResponseBody
	public ResponseEntity<?> updateBookPrice(@RequestBody Book book) {

		System.out.println("book data " + book.getBookId() + " " + book.getPrice());

		bookService.updateBookPrice(book.getBookId(), book.getPrice());

		System.out.println("Book Price is updated");
		return ResponseEntity.status(HttpStatus.OK).build();

	}
	
	@PostMapping("/deleteBook")
	@ResponseBody
	public ResponseEntity<?> deleteBook(@RequestBody Book book) {
		bookService.deleteBook(book.getBookId());
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/sales/{sellerName}")
	public ResponseEntity<?> getSalesInfo(@PathVariable String sellerName) {
		Seller seller = sellerService.getSellerByName(sellerName);
		Long sellerId = seller.getSellerId();
		
		List<Sales> sales = salesService.retriveSalesForSeller(sellerId);
		
		for(Sales sale : sales) {
			System.out.println("Sale id " + sale.getSaleId());
			System.out.println(sale.getSeller().getSellerName());
			
			System.out.println("Books  " + sale.getBook().getBookname());
		 }
		
		 if (sales.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } else {
	            return new ResponseEntity<>(sales, HttpStatus.OK);
	       }
		 
		 
	}
}
