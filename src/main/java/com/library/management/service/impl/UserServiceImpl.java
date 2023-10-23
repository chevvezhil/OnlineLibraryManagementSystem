package com.library.management.service.impl;

import java.util.List;

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
	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User authenticateUser(String username, String password) {
		User user = userRepository.findByUserName(username);

		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null; 
	}
	
	@Override
	public List<Seller> getUserByUserType(Roles role) {
		return userRepository.findByUserType(role);
	}
	
	@Override
	public String updateVerificationStatus(String sellerName, Long sellerId) {
		return userRepository.updateVerificationStatus(sellerName, sellerId);
	}

}
