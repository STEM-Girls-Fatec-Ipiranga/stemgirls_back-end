package com.br.femmcode.femmcode.dtos;

import java.time.Instant;
import java.util.List;

public class ComunidadeRespostaDTO {
    private String id;
    private String nome;
    private String descricao;
    private String nomeUsuarioCriador;
    private Instant criadaEm;
    private Instant atualizadaEm;
    private List<String> idsPosts;

    // getters e setters

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

    public Instant getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(Instant criadaEm) {
        this.criadaEm = criadaEm;
    }

    public Instant getAtualizadaEm() {
        return atualizadaEm;
    }

    public void setAtualizadaEm(Instant atualizadaEm) {
        this.atualizadaEm = atualizadaEm;
    }

    public List<String> getIdsPosts() {
        return idsPosts;
    }

    public void setIdsPosts(List<String> idsPosts) {
        this.idsPosts = idsPosts;
    }
}
