package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.domain.Seller;
import com.library.management.domain.User;
import com.library.management.utils.Roles;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	Seller findByUserType(Roles role);
}
