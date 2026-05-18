package com.br.femmcode.femmcode.models;

import com.br.femmcode.femmcode.enuns.Role;
import lombok.Data; // Usando Lombok para um código mais limpo
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "usuarios")
@Data
public class Usuario {
    
    @Id
    private String id;

    private String nome;
    private String nomeUsuario;

    private String email;
    private String senha;
    private String telefone;
    private String cpf;

    private String sobre;
    private String linkImagemPerfil;

    private String passwordResetToken;
    private LocalDateTime passwordResetTokenExpiryDate;

    private Role role;
    private final LocalDateTime dataEntrada = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }
}