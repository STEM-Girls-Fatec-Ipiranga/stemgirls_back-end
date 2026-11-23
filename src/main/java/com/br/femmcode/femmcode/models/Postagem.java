package com.br.femmcode.femmcode.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Document("postagens")
public class Postagem {
    @Id private String id;
    @Indexed private String comunidadeId;
    private String autorId;
    private String titulo;
    private String conteudo;
    private String imagem;
    private Set<String> curtidas = new HashSet<>();
    private Instant dataCriacao = Instant.now();

    public Postagem() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getComunidadeId() { return comunidadeId; }
    public void setComunidadeId(String comunidadeId) { this.comunidadeId = comunidadeId; }
    public String getAutorId() { return autorId; }
    public void setAutorId(String autorId) { this.autorId = autorId; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
    public Set<String> getCurtidas() { return curtidas; }
    public void setCurtidas(Set<String> curtidas) { this.curtidas = curtidas; }
    public Instant getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Instant dataCriacao) { this.dataCriacao = dataCriacao; }
}
