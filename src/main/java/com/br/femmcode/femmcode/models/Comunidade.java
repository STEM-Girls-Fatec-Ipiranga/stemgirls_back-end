package com.br.femmcode.femmcode.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Document("comunidades")
public class Comunidade {
    @Id private String id;
    private String nome;
    @Indexed(unique = true) private String nomeUsuario;
    private String descricao;
    private String imagem;
    private String donoId;
    private Set<String> membros = new HashSet<>();
    private Instant dataCriacao = Instant.now();

    public Comunidade() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
    public String getDonoId() { return donoId; }
    public void setDonoId(String donoId) { this.donoId = donoId; }
    public Set<String> getMembros() { return membros; }
    public void setMembros(Set<String> membros) { this.membros = membros; }
    public Instant getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Instant dataCriacao) { this.dataCriacao = dataCriacao; }
}