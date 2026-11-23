package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Postagem;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PostagemRepository extends MongoRepository<Postagem, String> {
    List<Postagem> findByComunidadeId(String comunidadeId, Sort sort);
}
