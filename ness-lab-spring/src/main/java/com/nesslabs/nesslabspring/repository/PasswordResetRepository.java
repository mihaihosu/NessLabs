package com.nesslabs.nesslabspring.repository;

import com.nesslabs.nesslabspring.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken,Long> {

    PasswordResetToken findByToken(String theToken);
}
