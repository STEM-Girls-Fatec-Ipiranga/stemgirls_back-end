package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.dtos.InscricaoDTO;
import com.br.femmcode.femmcode.models.Empresa;
import com.br.femmcode.femmcode.models.Evento;
import com.br.femmcode.femmcode.models.Inscricao;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.services.EventoService;
import com.br.femmcode.femmcode.services.InscricaoService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inscricao")
@CrossOrigin(origins = "http://localhost:5174")
public class InscricaoController {
    @Autowired
    private InscricaoService inscricaoService;

    @GetMapping("/{participanteId}")
    public ResponseEntity<List<Inscricao>> buscarPorParticipante(@PathVariable String participanteId){
        Optional<List<Inscricao>> inscricao = inscricaoService.buscarPorParticipante(participanteId);
        return inscricao.map(ResponseEntity::ok).orElse(null);
    }

    @GetMapping("/{participanteId}/{eventoId}")
    public ResponseEntity<Inscricao> encontrarInscricao(@PathVariable String participanteId, @PathVariable String eventoId){
        Optional<Inscricao> inscricao = inscricaoService.encontrarInscricao(participanteId, eventoId);
        return inscricao.map(ResponseEntity::ok).orElse(null);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirInscricao(@PathVariable String id) {
        String msg = inscricaoService.excluirInscricao(id);
        return ResponseEntity.ok(msg);
    }
}
