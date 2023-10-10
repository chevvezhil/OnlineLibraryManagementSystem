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
import com.library.management.storage.InMemoryAuthentication;

@RestController
@RequestMapping("/api/users")
public class AuthenticationController {

	@Autowired
	private  UserService userService;
	
	InMemoryAuthentication auth = new InMemoryAuthentication();

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> registerUser(@RequestBody User user) {
    	
    	String response = auth.register(user);
    	
    	return ResponseEntity.ok(response);
    	//TODO: enable Once db is working properly
      //  return userService.registerUser(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> loginUser(@RequestBody User user) {
       
    	System.out.println("user name " + user.getuserName() + " password " + user.getPassword());
    	
    	String role= auth.login(user.getuserName(), user.getPassword());
    	System.out.println("Role " + role);
    	return ResponseEntity.ok(role);
       
    	//TODO: enable Once db is working properly
    	// return userService.authenticateUser(username, password);
    }
	
}
