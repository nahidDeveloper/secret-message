package com.nahid.secretMessage.repository;

import com.nahid.secretMessage.user.User;
import com.nahid.secretMessage.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@DataMongoTest
@RunWith(SpringRunner.class)

public class UserRepositoryTests {

    //NOT recommended
    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        // Create a user and save it
        User user = User.builder()
                .email("test@example.com")
                .build();
        userRepository.save(user);

        // Find the user by email
        Optional<User> optionalUser = userRepository.findByEmail("test@example.com");
        assertTrue(optionalUser.isPresent());
        assertEquals("test@example.com", optionalUser.get().getEmail());
    }

    @Test
    public void testFindByEmailFail() {
        // Create a user and save it
        User user = User.builder()
                .email("test@example.com1")
                .build();
        userRepository.save(user);

        // Find the user by email
        Optional<User> optionalUser = userRepository.findByEmail("test@example.com123");
        assertFalse(optionalUser.isPresent());

    }
}
