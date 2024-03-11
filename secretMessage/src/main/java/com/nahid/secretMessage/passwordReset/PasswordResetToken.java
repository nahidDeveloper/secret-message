package com.nahid.secretMessage.passwordReset;

import com.nahid.secretMessage.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "passwordReset")
public class PasswordResetToken {
    @Id
    private String id;
    private String token;
    @DBRef(db="users")
    private User user;
    private LocalDateTime expiryDate;
}
