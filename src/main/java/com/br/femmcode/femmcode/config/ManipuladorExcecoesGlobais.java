package com.br.femmcode.femmcode.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.br.femmcode.femmcode.services.RecursoNaoEncontradoException;
import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class ManipuladorExcecoesGlobais {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<?> tratarNaoEncontrado(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(404).body(Map.of(
                "timestamp", Instant.now(),
                "mensagem", ex.getMessage()
        ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> tratarRequisicaoInvalida(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "timestamp", Instant.now(),
                "mensagem", ex.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> tratarGeral(Exception ex) {
        return ResponseEntity.status(500).body(Map.of(
                "timestamp", Instant.now(),
                "mensagem", "Erro interno: " + ex.getMessage()
        ));
    }
}
