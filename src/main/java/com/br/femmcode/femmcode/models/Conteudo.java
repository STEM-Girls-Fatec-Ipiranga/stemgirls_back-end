package com.br.femmcode.femmcode.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("conteudos")
public class Conteudo {

    @Id
    private String id;

    private String titulo;
    private String texto;
    private String imagemUrl; // opcional
}

