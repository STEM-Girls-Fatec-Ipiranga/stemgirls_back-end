package com.br.femmcode.femmcode.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notificacoes")
public class Notificacao {

    @Id
    private String id; // Mongo usa String para id
    private String empresaId;
    private String mensagem;
    private boolean lida = false; // começa como NÃO LIDA
    private String status;

    // CONSTRUTOR VAZIO (OBRIGATÓRIO para o MongoDB)
    public Notificacao() {}

    // CONSTRUTOR QUE VOCÊ ESTÁ USANDO NO SERVICE:
    public Notificacao(String mensagem, String empresaId) {
        this.mensagem = mensagem;
        this.empresaId = empresaId;
        this.lida = false;
    }

    // Getters e Setters...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmpresaId() { return empresaId; }
    public void setEmpresaId(String empresaId) { this.empresaId = empresaId; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public boolean isLida() { return lida; }
    public void setLida(boolean lida) { this.lida = lida; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
