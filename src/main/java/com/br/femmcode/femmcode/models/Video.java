package com.br.femmcode.femmcode.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "videos")
public class Video {

    @Id
    private String id;

    private String title;
    private String desc;
    private String url;         // URL pública (com timestamp)
    private String fileName;    // nome do arquivo salvo no disco (com timestamp)
    private String originalName; // nome original do arquivo (sem timestamp) - usado para checar duplicação
    private String canalId;
    private String owner;
    private String thumbnail;
    private long createdAt;

    public Video() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getOriginalName() { return originalName; }
    public void setOriginalName(String originalName) { this.originalName = originalName; }

    public String getCanalId() { return canalId; }
    public void setCanalId(String canalId) { this.canalId = canalId; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
