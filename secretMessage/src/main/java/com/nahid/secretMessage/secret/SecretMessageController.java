package com.nahid.secretMessage.secret;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SecretMessageController {

    @Autowired
    private SecretMessageService secretMessageService;

    @GetMapping("/secret-message")
    public ResponseEntity hi() {
        String message= secretMessageService.findSecretMessage().toString();
        return ResponseEntity.ok(message);
    }
}
