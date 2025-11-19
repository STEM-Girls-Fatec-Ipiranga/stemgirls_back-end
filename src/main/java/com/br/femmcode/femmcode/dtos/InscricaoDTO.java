package com.br.femmcode.femmcode.dtos;

public class InscricaoDTO {

    private String eventoId;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String instituicao;

    public InscricaoDTO() {}

    // GETTERS E SETTERS
    public String getEventoId() { return eventoId; }
    public void setEventoId(String eventoId) { this.eventoId = eventoId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }
}

