package com.br.femmcode.femmcode.models;

import com.br.femmcode.femmcode.enuns.Role;
import com.br.femmcode.femmcode.enuns.StatusEmpresa;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "empresas")
@Data
public class Empresa extends Usuario{

    private String cnpj;
    private StatusEmpresa status = StatusEmpresa.PENDENTE;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public StatusEmpresa getStatus() {
        return status;
    }

    public void setStatus(StatusEmpresa status) {
        this.status = status;
    }
}
