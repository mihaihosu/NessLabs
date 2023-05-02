package com.nesslabs.nesslabspring.repositories;

import com.nesslabs.nesslabspring.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken,Long> {

    Optional<PasswordResetToken> findByToken(String theToken);
}
