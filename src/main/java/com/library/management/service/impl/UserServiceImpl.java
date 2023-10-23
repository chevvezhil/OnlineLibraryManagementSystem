package com.library.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.management.domain.Seller;
import com.library.management.domain.User;
import com.library.management.repository.SellerRepository;
import com.library.management.repository.UserRepository;
import com.library.management.service.UserService;
import com.library.management.utils.VerificationStatus;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		var response = userRepository.save(user);
		
		if(user.getUserRole().equals("seller"))
		{
			var seller = new Seller(user.getUserId(), user.getUserName(), VerificationStatus.REQUESTED);
			sellerRepository.save(seller);
			System.out.println("Seller added to seller table");
		}

		return response;
	}

	@Override
	public User authenticateUser(String username, String password) {
		User user = userRepository.findByUserName(username);

		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null; 
	}
}
