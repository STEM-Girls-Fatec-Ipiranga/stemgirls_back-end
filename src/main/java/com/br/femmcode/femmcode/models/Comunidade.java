package com.br.femmcode.femmcode.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "comunidades")
public class Comunidade {
    @Id
    private String id;
    private String nome;
    private String descricao;
    private String nomeUsuarioCriador;
    private Instant criadaEm;
    private Instant atualizadaEm;
    private List<String> idsPosts = new ArrayList<>();

    public Comunidade() {}

    public Comunidade(String nome, String descricao, String nomeUsuarioCriador) {
        this.nome = nome;
        this.descricao = descricao;
        this.nomeUsuarioCriador = nomeUsuarioCriador;
        this.criadaEm = Instant.now();
        this.atualizadaEm = Instant.now();
    }

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