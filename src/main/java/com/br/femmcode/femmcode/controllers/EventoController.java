package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.models.Evento;
import com.br.femmcode.femmcode.services.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Evento>> listarEventos() {
        return ResponseEntity.ok(service.listarEventosPersistidos());
    }

    @PostMapping
    public ResponseEntity<Evento> criar(@RequestBody Evento evento) {
        Evento salvo = service.criarEvento(evento);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        service.excluirEvento(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
public ResponseEntity<Evento> atualizar(
        @PathVariable String id,
        @RequestBody Evento atualizado
) {
    return service.atualizarEvento(id, atualizado)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}}