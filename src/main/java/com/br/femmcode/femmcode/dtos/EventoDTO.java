package com.br.femmcode.femmcode.dtos;

import com.br.femmcode.femmcode.models.Endereco;
import com.br.femmcode.femmcode.models.Inscricao;
import com.br.femmcode.femmcode.models.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record EventoDTO(
        String id,

        @NotBlank(message = "O título do evento é obrigatório")
        String titulo,

        String modalidade,
        String descricao,

        @NotBlank(message = "Informe os dados do organizador")
        Usuario organizador,

        @NotBlank(message = "Informe a data e hora do evento")
        String data,
        String hora,

        @NotBlank(message = "Informe a localização do evento")
        Endereco endereco,

        String imagem,
        String linkInscricao,

        List<Inscricao> participantes
) {
}
