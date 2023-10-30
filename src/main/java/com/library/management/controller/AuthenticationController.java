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
@RequestMapping("/library/users")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		 if(userService.registerUser(user))
			 return ResponseEntity.ok("User registered successfully");
		 else
			 return ResponseEntity.badRequest().body("Username already exists");
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<String> loginUser(@RequestBody User user) {
		
		User userInfo = userService.authenticateUser(user.getUserName(), user.getPassword());

		if(userInfo.getUserRole().equalsIgnoreCase("seller") && !userService.isSellerVerified(user.getUserName())) 
				return ResponseEntity.badRequest().body("Thank you for registering with us. You will be verified by the admin to proceed further");
			
		return ResponseEntity.ok(userInfo.getUserRole());
		
	}

}
