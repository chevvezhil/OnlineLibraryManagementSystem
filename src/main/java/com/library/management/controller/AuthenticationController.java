package com.library.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.domain.User;
import com.library.management.service.UserService;

@RestController
@RequestMapping("/api/users")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public @ResponseBody User addNewUser(@RequestBody User user) {
		return userService.registerUser(user);
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<String> loginUser(@RequestBody User user) {

		User userInfo = userService.authenticateUser(user.getUserName(), user.getPassword());
		return ResponseEntity.ok(userInfo.getUserRole());
	}

}
