package com.br.femmcode.femmcode.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

@Document(collection = "inscricoes")
@Data
public class Inscricao {

    @Id
    private String id;

    @DocumentReference
    @Field(targetType = FieldType.OBJECT_ID)
    private Usuario participante;

    @DocumentReference
    @Field(targetType = FieldType.OBJECT_ID)
    private Evento evento;

    /*private String participanteId;*/
    private String eventoId;

    private String instituicao;
    private final LocalDateTime dataInscricao = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getParticipante() {
        return participante;
    }

    public void setParticipante(Usuario participante) {
        this.participante = participante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /*public String getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(String participanteId) {
        this.participanteId = participanteId;
    }*/

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }
}
