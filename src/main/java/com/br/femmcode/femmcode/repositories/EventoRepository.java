package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {
    Optional<List<Evento>> findByTitulo(String titulo);

    @Query("{ 'organizador': ?0 }")
    Optional<List<Evento>> findByOrganizador(String organizadorId);

    Boolean existsByTitulo(String titulo);
}
