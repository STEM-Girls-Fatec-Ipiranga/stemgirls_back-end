package com.br.femmcode.femmcode.dtos;

import com.br.femmcode.femmcode.models.Empresa;
import com.br.femmcode.femmcode.models.Notificacao;

public class NotificacaoRespostaDTO {
    private String id;
    private String mensagem;
    private String empresaId;

    public NotificacaoRespostaDTO() {
    }

    public String getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getEmpresaId() {
        return empresaId;
    }

}
