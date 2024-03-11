package com.nahid.secretMessage.passwordReset;

import com.nahid.secretMessage.user.User;
import com.nahid.secretMessage.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class PasswordResetController {

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/generate-token")
    public String generateResetToken(@RequestParam("email") String email){
        Optional<PasswordResetToken> existingToken = passwordResetTokenService.findTokenByUsername(email);
        existingToken.ifPresent(passwordResetToken -> passwordResetTokenService.delete(passwordResetToken));

        User user = userService.getUser(email);
        PasswordResetToken resetToken = passwordResetTokenService.createToken(user);
        String token = resetToken.getToken();
        return "Reset Token created:"+token;
    }

    @PostMapping("/reset-my-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @RequestParam("password") String password){
        PasswordResetToken resetToken = passwordResetTokenService.findByToken(token);
        if(resetToken==null){
            return ResponseEntity.badRequest().body("Invalid");
        }

        if(resetToken.getExpiryDate().isBefore(LocalDateTime.now())){
            return ResponseEntity.badRequest().body("Token has expired");
        }
        User user = resetToken.getUser();
        user.setPassword(password);
//        userService.save(user);

        passwordResetTokenService.delete(resetToken);
        return ResponseEntity.ok("Password Reset Successfully");
    }

}
