 package com.library.management.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.domain.Seller;
import com.library.management.domain.User;
import com.library.management.service.UserService;
import com.library.management.storage.InMemoryAuthentication;
import com.library.management.utils.Roles;

@RestController
public class UserController {

	@Autowired
	private  UserService userService;
	
	InMemoryAuthentication auth = new InMemoryAuthentication();
    
	@GetMapping("/api/sellerDetails")
    @ResponseBody
    public ResponseEntity<Seller> getSellerDetails() {
		Seller response = userService.getUserByUserType(Roles.SELLER);
    	return ResponseEntity.ok(response);
    }
	
}
