package com.library.management.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.domain.User;
import com.library.management.service.UserService;

@RestController
@RequestMapping("/api/users")
public class AuthenticationController {

	private  UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody Map<String,String> request) {
        String username = request.get("userName");
        String password = request.get("password");
        return userService.authenticateUser(username, password);
    }
	
}
