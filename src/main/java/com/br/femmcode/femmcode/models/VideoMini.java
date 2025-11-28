package com.br.femmcode.femmcode.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("videos")
public class VideoMini {

    @Id
    private String id;

    private String titulo;
    private String descricao;
    private String caminhoArquivo; // onde o vídeo ficou salvo no servidor
    private long tamanho;
    private String contentType;
}
