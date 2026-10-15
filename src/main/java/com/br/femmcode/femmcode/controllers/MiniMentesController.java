
package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.models.MiniMentes;
import com.br.femmcode.femmcode.repositories.MiniMentesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/minimentes")
@CrossOrigin(origins = "http://localhost:5173")
public class MiniMentesController {

    private final MiniMentesRepository repository;

    public MiniMentesController(MiniMentesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<MiniMentes> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MiniMentes> buscar(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MiniMentes> criar(
            @RequestBody MiniMentes conteudo) {

        conteudo.setId(null);

        MiniMentes salvo = repository.save(conteudo);

        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiniMentes> atualizar(
            @PathVariable String id,
            @RequestBody MiniMentes conteudo) {

        return repository.findById(id)
                .map(existente -> {
                    existente.setTitle(conteudo.getTitle());
                    existente.setDescription(conteudo.getDescription());
                    existente.setCategory(conteudo.getCategory());
                    existente.setDifficulty(conteudo.getDifficulty());
                    existente.setVideoLink(conteudo.getVideoLink());
                    existente.setThumbnail(conteudo.getThumbnail());
                    existente.setQuiz(conteudo.getQuiz());

                    return ResponseEntity.ok(repository.save(existente));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
