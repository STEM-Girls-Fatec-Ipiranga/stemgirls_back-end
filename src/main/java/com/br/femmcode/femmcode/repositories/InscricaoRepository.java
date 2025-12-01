package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Inscricao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InscricaoRepository extends MongoRepository<Inscricao, String> {
    List<Inscricao> findByEventoId(String eventoId);

 
}
