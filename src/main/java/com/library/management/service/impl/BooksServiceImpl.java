package com.library.management.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.management.domain.Book;
import com.library.management.service.BooksService;
import com.library.management.service.SearchService;
import com.library.management.storage.InMemoryBookStorage;

@Service
public class BooksServiceImpl implements BooksService {

	 @Value("${file.path}")
    private String uploadDirectory;
	
	@Override
	public void handleBookUpload(Book book) {
		
	try {
		MultipartFile file = book.getFile();
		
		if(!file.isEmpty()) {
             String fileName = book.getBookname() + ".pdf"; 
             String filePath = uploadDirectory + fileName;
             
             System.out.println("upload directory "  + uploadDirectory);
             
             File dest = new File(filePath);
             file.transferTo(dest);

             InMemoryBookStorage.addBook(book);
		}
		
	}catch(Exception e) {
		System.out.println("Exception " + e);
	}
		
	}

	@Override
	public void handleBookDownload(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> searchBook(String criteria, String keyword) {
		SearchService search  = new SearchService();
		search.setSearchStrategy(criteria);
		return search.searchBooks(keyword);
	}

}
