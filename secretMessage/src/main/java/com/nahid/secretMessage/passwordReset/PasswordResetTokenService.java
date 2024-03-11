package com.nahid.secretMessage.passwordReset;

import com.nahid.secretMessage.user.User;
import com.nahid.secretMessage.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordTokenResetRepository passwordTokenResetRepository;



    public PasswordResetToken createToken(User user){
        String token = generateToken();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);//One hour expiry

        //To create new Password Token
        PasswordResetToken  resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(expiryDate);
        return passwordTokenResetRepository.save(resetToken);
    }

    public  Optional<PasswordResetToken> findTokenByUsername(String email){
       return passwordTokenResetRepository.findByUserEmail(email);
    }

    public PasswordResetToken findByToken(String token){
        return passwordTokenResetRepository.findByToken(token).get();
    }

    public void delete(PasswordResetToken token){
        passwordTokenResetRepository.delete(token);
    }


    private String generateToken(){
        return UUID.randomUUID().toString();
    }
}
