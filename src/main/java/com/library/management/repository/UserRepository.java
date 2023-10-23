package com.library.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.domain.Seller;
import com.library.management.domain.User;
import com.library.management.utils.Roles;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

	User findByUsername(String username);
	List<Seller> findByUserType(Roles role);
	String updateVerificationStatus(String sellerName, Long sellerId);
}
