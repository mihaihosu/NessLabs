package com.nesslabs.nesslabspring.repositories;

import com.nesslabs.nesslabspring.entity.ConfirmationToken;
import com.nesslabs.nesslabspring.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class ConfirmationTokenRepositoryTest {

    private ConfirmationTokenRepository underTest;


    private UserRepository underTestUser;

    User user;
    ConfirmationToken confirmationToken;



    @Autowired
    ConfirmationTokenRepositoryTest(ConfirmationTokenRepository underTest, UserRepository underTestUser) {
        this.underTest = underTest;
        this.underTestUser = underTestUser;
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        underTestUser.deleteAll();
    }

    @BeforeEach()
    void setUserandToken(){
         user = new User("Mihai","mergeasergiu@gmail.com", "1234",false);
         confirmationToken = new ConfirmationToken("confirmationToken", LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);
    }
    @Test
    void itShouldReturnTheToken() {

        //given

        underTestUser.save(user);

        underTest.save(confirmationToken);

        //when
        Boolean existToken = underTest.findByToken("confirmationToken").isPresent();
        //then

        assertThat(existToken).isTrue();

    }

    @Test
    void itShouldSetConfirmationDateForToken() {

        underTestUser.save(user);

        confirmationToken.setConfirmedAt(LocalDateTime.now().plusMinutes(1));
        underTest.save(confirmationToken);

        ConfirmationToken token = underTest.findByToken("confirmationToken").orElse(null);

        assertNotNull(token.getConfirmedAt());

    }

    @Test
    void itShouldDeleteTokenByuser_Id() {

        underTestUser.save(user);

        underTest.save(confirmationToken);

        underTest.deleteByuser_Id(Math.toIntExact(user.getId()));

        ConfirmationToken foundToken = underTest.findByToken("confirmationToken").orElse(null);

        assertNull(foundToken);


    }
}