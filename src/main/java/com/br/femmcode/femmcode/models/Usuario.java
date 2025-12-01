package com.br.femmcode.femmcode.models;

import com.br.femmcode.femmcode.enuns.Role;
import lombok.Data; // Usando Lombok para um código mais limpo
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "usuarios")
@Data // Anotação do Lombok que cria getters, setters, construtores, etc.
public class Usuario {
    
    @Id
    private String id;

    private String nomeCompleto;
    private String nomeUsuario;

    private String email;
    private String senha;

    private String sobre;
    private String linkImagemPerfil;

    private String passwordResetToken;
    private LocalDateTime passwordResetTokenExpiryDate;

    private LocalDateTime joinDate;

    private Role role;

    // A anotação @Data do Lombok cuida de todos os getters e setters.
    // Não precisamos mais escrevê-los manualmente.

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public String getLinkImagemPerfil() {
        return linkImagemPerfil;
    }

    public void setLinkImagemPerfil(String linkImagemPerfil) {
        this.linkImagemPerfil = linkImagemPerfil;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public LocalDateTime getPasswordResetTokenExpiryDate() {
        return passwordResetTokenExpiryDate;
    }

    public void setPasswordResetTokenExpiryDate(LocalDateTime passwordResetTokenExpiryDate) {
        this.passwordResetTokenExpiryDate = passwordResetTokenExpiryDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}