package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.dtos.NotificacaoRespostaDTO;
import com.br.femmcode.femmcode.models.Empresa;
import com.br.femmcode.femmcode.models.Notificacao;
import com.br.femmcode.femmcode.repositories.EmpresaRepository;
import com.br.femmcode.femmcode.repositories.NotificacaoRepository;
import com.br.femmcode.femmcode.services.NotificacaoService;
import org.apache.tomcat.util.modeler.NotificationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    // LISTAR notificações não lidas (pendentes)
    @GetMapping("/pendentes")
    public ResponseEntity<List<Notificacao>> listar() {
        List<Notificacao> lista = notificacaoService.listarPendentes();
        return ResponseEntity.ok(lista);
    }

    // MARCAR como lida
    @PutMapping("/marcar-como-lida/{id}")
    public Notificacao marcarComoLida(@PathVariable String id) {
        Notificacao notif = notificacaoService.encontrarNotificacao(id);
        notif.setLida(true);
        return notificacaoService.atualizarNotificacao(notif);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<String> deletarNotificacao(@PathVariable String id) {
        notificacaoService.deletarNotificacao(id);
        return ResponseEntity.ok("Notificação apagada");
    }
}
