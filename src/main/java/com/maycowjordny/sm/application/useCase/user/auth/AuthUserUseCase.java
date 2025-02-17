package com.maycowjordny.sm.application.useCase.user.auth;

import com.maycowjordny.sm.domain.model.User;
import com.maycowjordny.sm.infra.repository.user.UserRepository;
import com.maycowjordny.sm.security.auth.JwtImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service
@RequiredArgsConstructor
public class AuthUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtImp jwt;

    public String execute(String email,String password) throws Exception {
        User user = userRepository.findByEmail(email);

        var passwordMatches = passwordEncoder.matches(password, user.getPassword());

        if (!passwordMatches) {
            throw new CredentialException("Credenciais inválidas");
        }

        return jwt.encode(user);
    }
}