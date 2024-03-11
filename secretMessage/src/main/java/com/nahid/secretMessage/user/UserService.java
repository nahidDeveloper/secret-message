package com.nahid.secretMessage.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        // Encode password before saving
        System.out.println(user.getEmail()+user.getFirstName()+user.getLastName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean existsByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            System.out.println("Already exists");
            return true;
        }
        return false;
    }

    public User getUser(String email){
        if(existsByEmail(email)){
            return userRepository.findByEmail(email).get();
        }
        throw new NoSuchElementException("User not found for email: " + email);
    }

    //    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }


}
