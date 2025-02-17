package com.maycowjordny.sm.infra.controller.user.auth;

import com.maycowjordny.sm.application.useCase.user.auth.AuthUserUseCase;
import com.maycowjordny.sm.infra.dto.UserAuthDtoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserUseCase authUserUseCase;

    @PostMapping
    public ResponseEntity<String> authUser(@Valid @RequestBody UserAuthDtoRequest authUserRequest) throws Exception {
        var result = authUserUseCase.execute(authUserRequest.getEmail(), authUserRequest.getPassword());
        return ResponseEntity.ok().body(result);
    }


}
