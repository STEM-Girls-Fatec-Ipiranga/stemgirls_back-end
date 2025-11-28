package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Conteudo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConteudoRepository extends MongoRepository<Conteudo, String> {
}

