package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.management.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    boolean existsByUserName(String userName);
}
