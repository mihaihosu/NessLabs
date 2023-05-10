package com.nesslabs.nesslabspring.repository;

import com.nesslabs.nesslabspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findAllByEmail(String email);

    List<User> findAllByUsername(String username);
}
