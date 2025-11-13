package com.br.femmcode.femmcode.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "videos")
public class Video {

    @Id
    private String id;
    private String title;
    private String desc;
    private String url;       // Base64 ou URL do vídeo
    private String thumb;
    private String fileName;     // Base64 ou URL da thumbnail
    private long createdAt;
    private String canalId;   // referência ao canal
    private String owner;     // "me" ou outro usuário
    
}
