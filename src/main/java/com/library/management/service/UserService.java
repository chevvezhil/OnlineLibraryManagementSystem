package com.library.management.service;

import com.library.management.domain.User;

public interface UserService {
	
	 User registerUser(User user);
	 User authenticateUser(String username, String password);

}
