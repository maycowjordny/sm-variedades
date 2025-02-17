package com.maycowjordny.sm.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Setter
@Getter
public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public static User create(
            UUID id,
            String name,
            String email,
            String password,
            Timestamp createdAt,
            Timestamp updatedAt
    ){
        return User.builder()
                .id((id == null) ? UUID.randomUUID(): id)
                .name(name)
                .email(email)
                .password(password)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
