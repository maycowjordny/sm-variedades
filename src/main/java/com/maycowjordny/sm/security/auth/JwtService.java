package com.maycowjordny.sm.security.auth;

import com.maycowjordny.sm.domain.model.User;

public interface JwtService {
    String encode(User user);
    String decode(String token);
}
