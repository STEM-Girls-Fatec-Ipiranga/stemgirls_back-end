package com.br.femmcode.femmcode.dtos;

import com.br.femmcode.femmcode.enuns.Role;
import com.br.femmcode.femmcode.models.StatusEmpresa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record UsuarioDTO (
        @NotBlank(message = "O nome do usuário é obrigatório")
        String nomeCompleto,

        @NotBlank(message = "O nome de usuário é obrigatório")
        String nomeUsuario,

        @Email(message = "E-mail é inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        String sobre,

        String linkImagemPerfil,

        String passwordResetToken,
        LocalDateTime passwordResetTokenExpiryDate,

        LocalDateTime joinDate,

        Role role
){ }
