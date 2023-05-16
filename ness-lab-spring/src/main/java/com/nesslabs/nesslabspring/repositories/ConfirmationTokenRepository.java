package com.nesslabs.nesslabspring.repositories;

import com.nesslabs.nesslabspring.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {


    Optional<ConfirmationToken> findByToken(@Param("token") String token);

    ConfirmationToken findByuser_Id(int table1Id);
}

