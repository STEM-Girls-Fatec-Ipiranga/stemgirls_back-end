package com.br.femmcode.femmcode.models;

import lombok.Data; // Usando Lombok para um código mais limpo
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "usuarios")
@Data // Anotação do Lombok que cria getters, setters, construtores, etc.
public class Usuario {
    
    @Id
    private String id; // Correto para MongoDB, que usa String como ID por padrão

    // Campos ajustados para corresponder ao formulário de cadastro do frontend
    private String nomeCompleto;
    private String nomeUsuario;

    private String email;
    private String senha;

    // Campos que usaremos mais tarde para a funcionalidade de "Esqueci a Senha"
    private String passwordResetToken;
    private LocalDateTime passwordResetTokenExpiryDate;

    // A anotação @Data do Lombok cuida de todos os getters e setters.
    // Não precisamos mais escrevê-los manualmente.
}