package com.library.management.service;

import org.springframework.stereotype.Service;

import com.library.management.domain.User;

@Service
public interface UserService {
	
	 boolean registerUser(User user);
	 User authenticateUser(String username, String password);
	 boolean isSellerVerified(String username);
	 public void removeUser(Long userId);
}
