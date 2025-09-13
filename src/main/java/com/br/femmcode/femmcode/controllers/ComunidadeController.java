package com.br.femmcode.femmcode.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.br.femmcode.femmcode.dtos.*;
import com.br.femmcode.femmcode.services.ComunidadeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comunidades")
public class ComunidadeController {

    private final ComunidadeService comunidadeService;

    public ComunidadeController(ComunidadeService comunidadeService) {
        this.comunidadeService = comunidadeService;
    }

    @PostMapping
    public ResponseEntity<ComunidadeRespostaDTO> criar(@Validated @RequestBody CriarComunidadeDTO dto) {
        ComunidadeRespostaDTO criada = comunidadeService.criar(dto);
        return ResponseEntity.created(URI.create("/api/comunidades/" + criada.getId())).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<ComunidadeRespostaDTO>> listarTodas() {
        return ResponseEntity.ok(comunidadeService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunidadeRespostaDTO> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(comunidadeService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComunidadeRespostaDTO> atualizar(@PathVariable String id,
                                                           @Validated @RequestBody AtualizarComunidadeDTO dto) {
        return ResponseEntity.ok(comunidadeService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        comunidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    /*@PostMapping("/{id}/posts")
    public ResponseEntity<ComunidadeRespostaDTO> adicionarPost(@PathVariable("id") String idComunidade,
                                                               @Validated @RequestBody AdicionarPostDTO dto) {
        ComunidadeRespostaDTO atualizada = comunidadeService.adicionarPost(idComunidade, dto.getIdPost());
        return ResponseEntity.ok(atualizada);
    }*/

    @DeleteMapping("/{id}/posts/{idPost}")
    public ResponseEntity<ComunidadeRespostaDTO> removerPost(@PathVariable("id") String idComunidade,
                                                             @PathVariable String idPost) {
        ComunidadeRespostaDTO atualizada = comunidadeService.removerPost(idComunidade, idPost);
        return ResponseEntity.ok(atualizada);
    }
}
