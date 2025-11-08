package com.br.femmcode.femmcode.dtos;

import com.br.femmcode.femmcode.models.StatusEmpresa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpresaDTO(

        @NotBlank(message = "O nome da empresa é obrigatório")
        String nomeEmpresa,

        @NotBlank(message = "O CNPJ é obrigatório")
        @Size(min = 14, max = 18, message = "CNPJ deve ter entre 14 e 18 caracteres")
        String cnpj,

        @NotBlank(message = "O nome fantasia é obrigatório")
        String nomeFantasia,

        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String senha,

        String telefone,

        String site,

        StatusEmpresa status
) {}
