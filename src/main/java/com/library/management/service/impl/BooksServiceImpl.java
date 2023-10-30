package com.library.management.service.impl;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.management.domain.Book;
import com.library.management.repository.BooksRepository;
import com.library.management.service.BooksService;
import com.library.management.service.SearchService;

import jakarta.transaction.Transactional;

@Service
public class BooksServiceImpl implements BooksService {

	@Value("${file.path}")
	private String uploadDirectory;

	@Autowired
	private BooksRepository bookRepository;
	
	@Autowired
	private SearchService searchService;

	@Override
	public void handleBookUpload(Book book, MultipartFile file) {

		try {

			if (!file.isEmpty()) {
				String filePath = uploadDirectory + book.getBookname() + ".pdf";

				book.setPdfUrl(filePath);

				File dest = new File(filePath);
				file.transferTo(dest);
				bookRepository.save(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e);
		}

	}

	@Override
	public List<Book> searchBook(String criteria, String keyword) {
		searchService.setSearchStrategy(criteria);
		return searchService.searchBooks(keyword,getAllBooks());
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	
	@Transactional
    public List<Book> getBooksBySeller(String sellerName) {
        if (sellerName != null) {
            return bookRepository.findBySeller(sellerName);
        }
        return Collections.emptyList();
    }
	
	public void updateBookPrice(Long bookId, double price) {
		var updateRecord = bookRepository.getReferenceById(bookId.toString());
		updateRecord.setPrice(price);
		bookRepository.save(updateRecord);
	}
	
	public void deleteBook(Long bookId) {
		bookRepository.deleteById(bookId.toString());
	}

}
