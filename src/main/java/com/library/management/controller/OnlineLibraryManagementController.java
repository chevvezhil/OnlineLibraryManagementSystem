package com.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.library.management.domain.Admin;
import com.library.management.domain.Book;
import com.library.management.domain.Order;
import com.library.management.domain.Seller;
import com.library.management.dto.OrderDTO;
import com.library.management.order.OrderDownloadHandler;
import com.library.management.order.OrderHandler;
import com.library.management.order.OrderValidationHandler;
import com.library.management.order.PaymentProcessingHandler;
import com.library.management.service.BooksService;
import com.library.management.service.OrderService;
import com.library.management.service.SalesService;
import com.library.management.utils.OrderStatus;

@RestController
@RequestMapping("/library")
public class OnlineLibraryManagementController {

	@Autowired
	private BooksService bookService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SalesService salesService;

	@GetMapping("/searchBooks/{criteria}/{keyword}")
	@ResponseBody
	public List<Book> searchBooks(@PathVariable String criteria, @PathVariable String keyword) {
		return bookService.searchBook(criteria, keyword);
	}

	@GetMapping(value = "/getBooks")
	public @ResponseBody List<Book> getAllBooks() {
		return bookService.getAllBooks();

	}

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

	@PostMapping("/processOrder")
	public ResponseEntity<String> processOrder(@RequestBody OrderDTO orderDto) {

		
		Order order = new Order();
		order.setBooks(orderDto.getBooks());
		order.setBuyerId(orderDto.getBuyerId());
		
		Seller seller = new Seller();
		Admin admin = new Admin();

		order.attach(admin);
		order.attach(seller);
		
		System.out.println("controller ");

		OrderHandler orderProcessingChain = new OrderValidationHandler(
				new PaymentProcessingHandler(new OrderDownloadHandler(null)));

		String zipFiles = orderProcessingChain.processOrder(order);
		order.setOrderStatus(OrderStatus.SUCCESS);
		
		System.out.println("Order processed ");
		
		orderService.createOrder(order);
		
		System.out.println("Order created ");
		salesService.recordSales(order);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=books.zip");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/zip"))
				.body(zipFiles);

	}
	

}
