package com.nahid.secretMessage.passwordReset;


import com.nahid.secretMessage.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordTokenResetRepository  extends MongoRepository<PasswordResetToken,String> {
    Optional<PasswordResetToken> findByToken(String token);
    @Query("{'user.email': ?0}")
    Optional<PasswordResetToken>findByUserEmail(String email);

    Optional<PasswordResetToken>findByUser_Email(String email);


}
