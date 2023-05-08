package com.nesslabs.nesslabspring.repository;

import com.nesslabs.nesslabspring.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfUserExists() {

        String email = "cristiana@yahoo.com";
        User user = User
                .builder()
                .username("Cristiana")
                .email(email)
                .password("dogs")
                .is_admin(false)
                .build();

        User expected = underTest.save(user);
        Optional<User> actual = underTest.findByEmail(email);
        actual.ifPresent(value -> assertThat(value).isEqualTo(expected));


    }
}