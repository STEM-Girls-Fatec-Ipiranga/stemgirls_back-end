package com.br.femmcode.femmcode.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;

public class Dtos {
    public record ComunidadeRequest(
            @NotBlank String nome,
            @NotBlank String nomeUsuario,
            String descricao,
            @Size(max = 2_000_000) String imagem,
            @NotBlank String usuarioId) {}

    public record PostagemRequest(
            @NotBlank String titulo,
            @NotBlank String conteudo,
            @Size(max = 2_000_000) String imagem,
            @NotBlank String usuarioId) {}

    public record ComunidadeResponse(
            String id, String nome, String nomeUsuario, String descricao, String imagem,
            int totalMembros, boolean souDono, boolean souMembro) {}

    public record PostagemResponse(
            String id, String comunidadeId, String comunidadeNome, String comunidadeImagem,
            String titulo, String conteudo, String imagem, Instant dataCriacao,
            int curtidas, boolean curtiu, boolean souMembro) {}
}