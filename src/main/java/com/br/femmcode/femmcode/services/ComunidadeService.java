package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.dtos.*;
import java.util.List;

public interface ComunidadeService {
    ComunidadeRespostaDTO criar(CriarComunidadeDTO dto);
    List<ComunidadeRespostaDTO> listarTodas();
    ComunidadeRespostaDTO buscarPorId(String id);
    ComunidadeRespostaDTO atualizar(String id, AtualizarComunidadeDTO dto);
    void deletar(String id);
    ComunidadeRespostaDTO adicionarPost(String idComunidade, String idPost);
    ComunidadeRespostaDTO removerPost(String idComunidade, String idPost);
}