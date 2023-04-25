package com.nesslabs.nesslabspring.user.repositories;

import com.nesslabs.nesslabspring.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
