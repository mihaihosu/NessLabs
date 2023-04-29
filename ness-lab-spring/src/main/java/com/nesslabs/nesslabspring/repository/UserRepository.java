package com.nesslabs.nesslabspring.repository;

import com.nesslabs.nesslabspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    //Optional<User> findByUsername(String username);
}
