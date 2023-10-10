package com.library.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.management.domain.Book;
import com.library.management.domain.User;
import com.library.management.repository.UserRepository;
import com.library.management.service.BooksService;

@Controller
@RequestMapping(path = "/library")
public class OnlineLibraryManagementController {

	private UserRepository userRepository;
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

	@PostMapping("/upload")
	public String handleFileUpload(@RequestBody Book book) {

		return "";

	}

}
