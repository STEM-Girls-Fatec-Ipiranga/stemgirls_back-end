package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Evento;
import com.br.femmcode.femmcode.models.Inscricao;
import com.br.femmcode.femmcode.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InscricaoRepository extends MongoRepository<Inscricao, String> {
    @Query("{ 'participante': ?0 }")
    Optional<List<Inscricao>> findByParticipante(String participanteId);

    @Query("{ 'participante': ?0, 'evento': ?1 }")
    Optional<Inscricao> findByParticipanteAndEvento(String participanteId, String eventoId);

    @Query("{ 'evento': ?0 }")
    Optional<List<Inscricao>> findByEvento(String eventoId);
}
