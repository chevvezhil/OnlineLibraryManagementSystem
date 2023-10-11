package com.library.management.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.management.domain.Seller;
import com.library.management.domain.User;
import com.library.management.repository.UserRepository;
import com.library.management.service.UserService;
import com.library.management.utils.Roles;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public String registerUser(User user) {
		user.setUserName(user.getuserName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserRole(user.getUserRole());
		 userRepository.save(user);
		 
		System.out.println("registration success ");

		 return "Success";
	}

	@Override
	public User authenticateUser(String username, String password) {
		User user = userRepository.findByUsername(username);
		
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null; // TODO: throw exception - Authentication failed
	}
	
	@Override
	public Seller getUserByUserType(Roles role) {
		return userRepository.findByUserType(role);
	}

}
