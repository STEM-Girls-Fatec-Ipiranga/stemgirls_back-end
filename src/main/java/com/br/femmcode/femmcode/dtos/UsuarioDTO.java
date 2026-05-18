package com.br.femmcode.femmcode.dtos;

import com.br.femmcode.femmcode.enuns.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record UsuarioDTO (
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O nome de usuário é obrigatório")
        String nomeUsuario,

        @Email(message = "E-mail é inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        String cpf,
        String telefone,
        String sobre,
        String linkImagemPerfil,

        String passwordResetToken,
        LocalDateTime passwordResetTokenExpiryDate,

        Role role
){ }
