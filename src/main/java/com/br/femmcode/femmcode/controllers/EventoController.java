package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.dtos.EventoDTO;
import com.br.femmcode.femmcode.dtos.InscricaoDTO;
import com.br.femmcode.femmcode.models.Evento;
import com.br.femmcode.femmcode.models.Inscricao;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.services.EventoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evento")
@CrossOrigin(origins = "http://localhost:5174")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping()
    public ResponseEntity<List<Evento>> listarEventos() {
        List<Evento> eventos = eventoService.listarEventos();
        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/criar")
    public ResponseEntity<Evento> criarEvento(@RequestPart("evento") EventoDTO dto, @RequestPart("imagem") MultipartFile imagem) throws IOException {
        Evento evento = eventoService.criarEvento(dto, imagem);
        return ResponseEntity.ok(evento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> encontrarEvento(@PathVariable String id) {
        Optional<Evento> evento = eventoService.encontrarEvento(id);
        return evento.map(ResponseEntity::ok).orElse(null);
    }

    @PostMapping("/atualizar/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable String eventoId, @RequestBody EventoDTO dto) {
        Evento evento = eventoService.atualizarEvento(eventoId, dto);
        return ResponseEntity.ok(evento);
    }

    @GetMapping("/pesquisar/{titulo}")
    public ResponseEntity<List<Evento>> pesquisarEvento(@PathVariable String titulo) {
        Optional<List<Evento>> evento = eventoService.pesquisarEvento(titulo);
        return evento.map(ResponseEntity::ok).orElse(null);
    }

    @GetMapping("/organizador/{organizadorId}")
    public ResponseEntity<List<Evento>> buscarMeusEventos(@PathVariable String organizadorId) {
        Optional<List<Evento>> meusEventos = eventoService.buscarMeusEventos(organizadorId);
        return meusEventos.map(ResponseEntity::ok).orElse(null);
    }

    @PostMapping("/{eventoId}/inscrever")
    public ResponseEntity<Inscricao> adicionarInscricao(@PathVariable String eventoId, @RequestBody InscricaoDTO dto){
        Inscricao inscricao = eventoService.adicionarInscricao(eventoId, dto);
        return ResponseEntity.ok(inscricao);
    }

    @GetMapping("/{eventoId}/listar/inscricoes")
    public ResponseEntity<List<Inscricao>> listarParticipantes(@PathVariable String eventoId) {
        Optional<List<Inscricao>> listaInscricoes = eventoService.listarParticipantes(eventoId);
        return listaInscricoes.map(ResponseEntity::ok).orElse(null);
    }

    @GetMapping("/{eventoId}/download/inscricoes")
    public void baixarInscricoes(@PathVariable String eventoId, HttpServletResponse response) throws IOException {
        Optional<List<Inscricao>> inscricoes = eventoService.listarParticipantes(eventoId);
        response.setContentType("text/csv");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=inscricoes_evento_" + eventoId + ".csv"
        );

        if(inscricoes.isPresent()){
            eventoService.exportarInscrcicoesCSV(response.getWriter(), inscricoes.get());
        }else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Nenhuma inscrição encontrada para este evento.");
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@PathVariable String id) {
        String msg = eventoService.excluirEvento(id);
        return ResponseEntity.ok(msg);
    }

}