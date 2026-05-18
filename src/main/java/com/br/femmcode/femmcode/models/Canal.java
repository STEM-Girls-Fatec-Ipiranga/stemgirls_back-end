package com.br.femmcode.femmcode.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "canais")
@Data
public class Canal {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private String banner;
    private String fotoPerfil;
    private int inscritos;
    private String owner;

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getBanner() { return banner; }
    public void setBanner(String banner) { this.banner = banner; }

    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }

    public int getInscritos() { return inscritos; }
    public void setInscritos(int inscritos) { this.inscritos = inscritos; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
}

