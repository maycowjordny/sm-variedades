package com.maycowjordny.sm.infra.controller.user;

import com.maycowjordny.sm.application.useCase.user.CreateUserUseCase;
import com.maycowjordny.sm.infra.dto.UserDtoRequest;
import com.maycowjordny.sm.infra.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<UUID> post(@Valid @RequestBody UserDtoRequest user) {
        final var result = createUserUseCase.execute(UserMapper.convertRequestUserDtoToDomain(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
