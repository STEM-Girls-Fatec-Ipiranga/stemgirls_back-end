package com.br.femmcode.femmcode.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("quizzes")
public class Quiz {

    @Id
    private String id;

    private String categoria; // lógica, matemática, português etc
    private String pergunta;
    private List<String> opcoes;
    private int respostaCorreta; // índice
}

