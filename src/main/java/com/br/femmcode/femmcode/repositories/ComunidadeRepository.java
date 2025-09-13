package com.br.femmcode.femmcode.repositories;


import com.br.femmcode.femmcode.models.Comunidade;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ComunidadeRepository extends MongoRepository<Comunidade, String> {
    Optional<Comunidade> findByNome(String nome);
    boolean existsByNome(String nome);
}
