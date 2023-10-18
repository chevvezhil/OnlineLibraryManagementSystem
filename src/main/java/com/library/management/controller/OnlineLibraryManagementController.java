package com.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.library.management.domain.User;
import com.library.management.service.BooksService;
import com.library.management.service.UserService;

@RestController
@RequestMapping("/library")
public class OnlineLibraryManagementController {

	@Autowired
	private UserService userService;

	@Autowired
	private BooksService bookService;

	@GetMapping("/status")
	public String getStatus() {
		return "Active Library";
	}

	@PostMapping("/registerUser")
	public @ResponseBody User addNewUser(@RequestBody User user) {
		return userService.registerUser(user);
	}

	@PostMapping("/uploadBook")
	@ResponseBody
	public String handleFileUpload(@RequestPart("pdfFile") MultipartFile file,   @RequestParam("bookname") String bookName,
		    @RequestParam("author") String author,
		    @RequestParam("genre") String genre,
		    @RequestParam("price") double price) {
		
		System.out.println("book data " + bookName + " " + author + " " + genre + " " + price);
		
		Book book = new Book(bookName,author,genre,price);
		bookService.handleBookUpload(book, file);

		System.out.println("Book uploaded");
		return "Ok";

	}
	
	@GetMapping("/searchBooks/{criteria}/{keyword}")
	@ResponseBody
	public List<Book> searchBooks(@PathVariable String criteria, @PathVariable String keyword){
		return bookService.searchBook(criteria, keyword);
	}
	
	@GetMapping(value = "/getBooks")
	public @ResponseBody List<Book> getAllBooks(){
        return bookService.getAllBooks();

	}
	
	public @ResponseBody String makeOrder(@RequestParam("books") List<Book> books) {
		return "";
	}
	
	
}
