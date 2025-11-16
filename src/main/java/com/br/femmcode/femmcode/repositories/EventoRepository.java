package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {
   List<Evento> findByEmpresa(String empresa);
}
