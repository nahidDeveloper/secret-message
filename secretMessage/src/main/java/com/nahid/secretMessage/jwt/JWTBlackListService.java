package com.nahid.secretMessage.jwt;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JWTBlackListService {

    // Set to store invalidated tokens
    private final Set<String> blacklist = new HashSet<>();

    /**
     * To add token
     * @param token
     */
    public void addToBlacklist(String token) {
        blacklist.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklist.contains(token);
    }

    public void removeFromBlacklist(String token) {
        blacklist.remove(token);
    }

}
