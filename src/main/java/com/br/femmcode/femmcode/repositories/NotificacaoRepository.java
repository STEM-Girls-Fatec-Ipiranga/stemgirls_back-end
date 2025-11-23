package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Notificacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotificacaoRepository extends MongoRepository<Notificacao, String> {
    List<Notificacao> findByLidaFalse();          // buscar notificações pendentes
    Notificacao findByEmpresaId(String id); // buscar notificações de uma empres
}
