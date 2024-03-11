package com.nahid.secretMessage.auth;

import com.nahid.secretMessage.jwt.JWTBlackListService;
import com.nahid.secretMessage.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JWTBlackListService jwtBlackListService;

    @GetMapping("/sign-out")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // Extract token from Authorization header
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            jwtBlackListService.addToBlacklist(token);
            return ResponseEntity.noContent().build(); // Return 204 No Content for successful logout
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized"); // Return 401 Unauthorized if token is not provided or invalid
        }
    }


    @PostMapping("/sign-in")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Incorrect email or password", HttpStatus.UNAUTHORIZED);
        }

        final String token = jwtUtil.generateToken(authenticationRequest.getEmail());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
