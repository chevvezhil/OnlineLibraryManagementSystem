package com.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.library.management.domain.Book;
import com.library.management.domain.User;
import com.library.management.repository.UserRepository;
import com.library.management.service.BooksService;

@Controller
@RequestMapping(path = "/library")
public class OnlineLibraryManagementController {

	private UserRepository userRepository;
	
	@Autowired
	private BooksService bookService;

	@RequestMapping(method = RequestMethod.GET, path = "/status")
	@ResponseBody
	public String getStatus() {
		return "Active Library";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/add")
	public @ResponseBody String addNewUser() {

		User user = new User();
		user.setUserName("Chevvanthi");
		user.setUserRole("Buyer");

		userRepository.save(user);
		return "Saved";
	}

	@PostMapping("/uploadBook")
	public String handleFileUpload(@RequestPart("pdfFile") MultipartFile file,   @RequestParam("bookname") String bookName,
		    @RequestParam("author") String author,
		    @RequestParam("genre") String genre,
		    @RequestParam("price") double price) {
		
		System.out.println("book data " + bookName + " " + author + " " + genre + " " + price);
		
		Book book = new Book(bookName,author,genre,price,file);
		bookService.handleBookUpload(book);
		System.out.println("Book uploaded");
		return "Ok";

	}
	
	@PostMapping("/SearchBook")
	public List<Book> searchBooks(@RequestParam String criteria, @RequestParam String keyword){
		return bookService.searchBook(criteria, keyword);
	}

}
