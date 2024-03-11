package com.nahid.secretMessage.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserService userService;



    @GetMapping("/admin")
    public ResponseEntity hiAdmin(){
        return ResponseEntity.ok("Hello from securedEndPoint");
    }
    @GetMapping("/hi")
    public ResponseEntity hi(){
            return ResponseEntity.ok("Hello from securedEndPoint");
        }

    @PostMapping("/sign-up")
    public ResponseEntity registerUser(@RequestBody User user){
        try{
            if(userRepository.findByEmail(user.getEmail()).isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User exist");

            }
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userRepository.save(user);
            userService.registerUser(user);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
