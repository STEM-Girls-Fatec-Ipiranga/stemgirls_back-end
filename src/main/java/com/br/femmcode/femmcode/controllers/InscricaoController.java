package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.dtos.InscricaoDTO;
import com.br.femmcode.femmcode.models.Inscricao;
import com.br.femmcode.femmcode.services.InscricaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricoes")
@CrossOrigin(
    originPatterns = "*",
    allowedHeaders = "*",
    allowCredentials = "true"
)
public class InscricaoController {

    private final InscricaoService service;

    public InscricaoController(InscricaoService service) {
        this.service = service;
    }

    @PostMapping
    public Inscricao criar(@RequestBody InscricaoDTO dto) {
        return service.criarInscricao(dto);
    }

    @GetMapping
    public List<Inscricao> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/evento/{eventoId}")
    public List<Inscricao> listarPorEvento(@PathVariable String eventoId) {
        return service.listarPorEvento(eventoId);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}
