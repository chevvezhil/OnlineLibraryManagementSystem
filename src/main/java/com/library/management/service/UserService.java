package com.library.management.service;

import org.springframework.stereotype.Service;

import com.library.management.domain.User;

@Service
public interface UserService {
	
	 User registerUser(User user);
	 User authenticateUser(String username, String password);

}
