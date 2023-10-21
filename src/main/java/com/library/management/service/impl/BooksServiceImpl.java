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
import com.library.management.utils.ProjectUtils;

@Service
public class BooksServiceImpl implements BooksService {

	@Value("${file.path}")
	private String uploadDirectory;

	@Override
	public void handleBookUpload(Book book, MultipartFile file) {

		try {

			if (!file.isEmpty()) {
				String filePath = uploadDirectory + book.getBookname() + ".pdf";
				book.setPdfUrl(filePath);

				File dest = new File(filePath);
				file.transferTo(dest);

				book.setBookId(ProjectUtils.getId());
				InMemoryBookStorage.addBook(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e);
		}

	}

	@Override
	public String handleBookDownload(Book book) {

		return "";
	}

	
	@Override
	public List<Book> searchBook(String criteria, String keyword) {
		SearchService search = new SearchService();
		search.setSearchStrategy(criteria);
		return search.searchBooks(keyword);
	}

	public List<Book> getAllBooks() {
		return InMemoryBookStorage.getAllBooks();
	}

}
