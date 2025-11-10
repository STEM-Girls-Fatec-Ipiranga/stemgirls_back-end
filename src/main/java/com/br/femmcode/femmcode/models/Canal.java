package com.br.femmcode.femmcode.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "canais")
public class Canal {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private String banner;
    private String fotoPerfil;
    private int inscritos;
    private String owner;
}
