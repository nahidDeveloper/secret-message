package com.nahid.secretMessage.secret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretMessageService {

    @Autowired
    private SecretMessageRepository secretMessageRepository;

    /**
     * We only adding one secret message currently
     * @return
     */
    public SecretMessage findSecretMessage(){
       return secretMessageRepository.findAll().get(0);
    }
}
