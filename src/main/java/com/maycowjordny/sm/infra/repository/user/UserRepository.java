package com.maycowjordny.sm.infra.repository.user;

import com.maycowjordny.sm.domain.model.User;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
}
