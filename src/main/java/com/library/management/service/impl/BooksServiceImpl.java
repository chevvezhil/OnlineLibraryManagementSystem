package com.library.management.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.library.management.domain.Book;
import com.library.management.storage.InMemoryBookStorage;

public class BooksServiceImpl implements com.library.management.service.BooksService {

	@Value("file.path")
    private String uploadDirectory;
	
	@Override
	public void handleBookUpload(Book book) {
		
	try {
		MultipartFile file = book.getFile();
		if(!file.isEmpty()) {
             String fileName = book.getBookname() + ".pdf"; 
             String filePath = uploadDirectory + fileName;
             
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

}
