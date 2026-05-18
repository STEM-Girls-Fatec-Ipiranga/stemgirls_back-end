package com.br.femmcode.femmcode.dtos;

import com.br.femmcode.femmcode.models.Evento;
import com.br.femmcode.femmcode.models.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record InscricaoDTO (
    String id,

    @NotBlank(message = "Insira os dados do participante")
    String participanteId,

    @NotBlank(message = "Evento não pode ser nulo")
    String eventoId,

    String instituicao
) {}

