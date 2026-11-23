package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Comunidade;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ComunidadeRepository extends MongoRepository<Comunidade, String> {
    List<Comunidade> findByNomeContainingIgnoreCaseOrNomeUsuarioContainingIgnoreCase(String nome, String usuario);
    List<Comunidade> findByDonoId(String donoId);
    List<Comunidade> findByMembrosContainingAndDonoIdNot(String usuarioId, String donoId);
    boolean existsByNomeUsuarioIgnoreCase(String nomeUsuario);
}