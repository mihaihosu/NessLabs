package com.nesslabs.nesslabspring.repositories;

import com.nesslabs.nesslabspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.is_confirmed = TRUE WHERE a.email =?1")
    int enableUser(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.username =:username WHERE a.email =:email")
    void updateUsername(@Param("email") String email, @Param("username") String username);

    @Transactional
    @Modifying
    @Query("UPDATE User a SET a.password =:password WHERE a.email =:email" )
    void updatePassword(@Param("email") String email, @Param("password") String password);

}