package com.maycowjordny.sm.infra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserAuthDtoRequest {
    @NotNull(message = "O email não pode estar vazio.")
    @Email(message = "Email é um campo obrigatório")
    private String email;

    @NotBlank(message = "A senha não pode estar vazia.")
    @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres.")
    private String password;
}
