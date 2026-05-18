package com.br.femmcode.femmcode.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "eventos")
public class Evento {

    @Id
    private String id;

    @DocumentReference
    @Field(targetType = FieldType.OBJECT_ID)
    private Usuario organizador;

    private String titulo;
    private String descricao;
    private Endereco endereco;

    private String data;
    private String hora;
    private String modalidade;

    private String imagem;
    private String linkInscricao;
    private String linkEventoOnline;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getLinkInscricao() {
        return linkInscricao;
    }

    public void setLinkInscricao(String linkInscricao) {
        this.linkInscricao = linkInscricao;
    }

    public String getLinkEventoOnline() {
        return linkEventoOnline;
    }

    public void setLinkEventoOnline(String linkEventoOnline) {
        this.linkEventoOnline = linkEventoOnline;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
