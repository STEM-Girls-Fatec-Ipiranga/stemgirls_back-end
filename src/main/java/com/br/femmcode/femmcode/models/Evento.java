package com.br.femmcode.femmcode.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "eventos")
public class Evento {

    @Id
    private String id;

    private String empresa;
    private String titulo;
    private String data;
    private String hora;
    private String tipo;
    private String local;
    private String descricao;
    private String imagem;
    private String link;
    private String enderecoCompleto;

    // TIPADO CORRETAMENTE
    private List<Inscricao> participantes = new ArrayList<>();
}
