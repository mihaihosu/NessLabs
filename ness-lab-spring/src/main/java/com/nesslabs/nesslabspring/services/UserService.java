package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    void enableUser(String email);

    String signUpUser(User user);

}
