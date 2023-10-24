package com.library.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.management.domain.Seller;
import com.library.management.domain.User;
import com.library.management.utils.Roles;

@Service
public interface UserService {
	
	 User registerUser(User user);
	 User authenticateUser(String username, String password);
}
