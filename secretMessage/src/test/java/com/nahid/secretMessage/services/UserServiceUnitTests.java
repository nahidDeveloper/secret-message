package com.nahid.secretMessage.services;




import com.nahid.secretMessage.user.User;
import com.nahid.secretMessage.user.UserRepository;
import com.nahid.secretMessage.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceUnitTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private List<User> listOfUsers;

    @Before
    public void setUp() {
        listOfUsers = new ArrayList<>();
        User user1 = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("password")
                .role("USER")
                .build();
        User user2 = User.builder()
                .firstName("Jay")
                .lastName("han")
                .email("jay@example.com")
                .password("password2")
                .role("USER")
                .build();

        listOfUsers.add(user1);
        listOfUsers.add(user2);

    }

    @Test
    public void testRegisterUser(){
        //Mocking our password encoder
        when(passwordEncoder.encode(listOfUsers.get(0).getPassword())).thenReturn("encodedPassword");
        //Mocking UserRepo
        // Mock behavior of userRepository.save()
        when(userRepository.save(any(User.class))).thenReturn(listOfUsers.get(0));

        // Act
        User savedUser = userService.registerUser(listOfUsers.get(0));

        // Assert
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(listOfUsers.get(0));
        assertEquals("encodedPassword", listOfUsers.get(0).getPassword());
    }

    @Test
    public void testExistsByEmail_whenUserExists() {
        // Mock behavior of userRepository.findByEmail()
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(new User()));


        boolean exists = userService.existsByEmail("test@example.com");


        assertTrue(exists);
    }

    @Test
    public void testExistsByEmail_whenUserDoesNotExist() {
        // Mock behavior of userRepository.findByEmail()
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        // Call the method under test
        boolean exists = userService.existsByEmail("test@example.com");

        // Verify the result
        assertFalse(exists);
    }
}
