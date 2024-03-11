package com.nahid.secretMessage.config;

import com.nahid.secretMessage.user.User;
import com.nahid.secretMessage.user.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitialiser {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initializeAdminUser() {
        // Check if admin user already exists
        if (!userService.existsByEmail("admin")) {
            // Create admin user
            User adminUser = User.builder()
                    .firstName("Admin")
                    .lastName("User")
                    .email("admin")
                    .password("admin")
                    .role("ADMIN")
                    .build();
            userService.registerUser(adminUser);
        }
    }
}
