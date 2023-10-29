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
import com.library.management.service.BooksService;

@RestController
@RequestMapping("/library/book")
public class BookController {

	@Autowired
	private BooksService bookService;

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
	public ResponseEntity<?> uploadBook(@RequestPart("pdfFile") MultipartFile file,
			@RequestParam("bookname") String bookName, @RequestParam("author") String author,
			@RequestParam("genre") String genre, @RequestParam("price") double price,
			@RequestParam("seller") String sellerName) {


		Book book = new Book(bookName, author, genre, price, sellerName);
		bookService.handleBookUpload(book, file);

		System.out.println("Book has been added successfully");
		return ResponseEntity.status(HttpStatus.OK).build();

	}
	
	@PostMapping("/updateBookPrice")
	@ResponseBody
	public ResponseEntity<?> updateBookPrice(@RequestBody Book book) {

		bookService.updateBookPrice(book.getBookId(), book.getPrice());
		System.out.println("Book price updated");
		return ResponseEntity.status(HttpStatus.OK).build();

	}
	
	@PostMapping("/deleteBook")
	@ResponseBody
	public ResponseEntity<?> deleteBook(@RequestBody Book book) {
		bookService.deleteBook(book.getBookId());
		System.out.println("Book has been deleted successfully");

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/books/{sellerName}")
    public ResponseEntity<List<Book>> getBooksBySellerName(@PathVariable String sellerName) {
        List<Book> books = bookService.getBooksBySeller(sellerName);

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }

}
