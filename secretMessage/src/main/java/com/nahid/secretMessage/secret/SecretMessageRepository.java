package com.nahid.secretMessage.secret;

import com.nahid.secretMessage.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretMessageRepository  extends MongoRepository<SecretMessage,String> {
}
