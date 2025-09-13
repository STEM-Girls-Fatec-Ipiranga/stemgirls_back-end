package com.br.femmcode.femmcode.dtos;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

public class CriarComunidadeDTO {
    //@NotBlank
    //@Size(min = 3, max = 80)
    private String nome;

    //@Size(max = 1000)
    private String descricao;

    private String nomeUsuarioCriador;

    // getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeUsuarioCriador() {
        return nomeUsuarioCriador;
    }

    public void setNomeUsuarioCriador(String nomeUsuarioCriador) {
        this.nomeUsuarioCriador = nomeUsuarioCriador;
    }
}
