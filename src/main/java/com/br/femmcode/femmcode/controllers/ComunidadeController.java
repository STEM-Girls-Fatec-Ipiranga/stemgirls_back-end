package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.dtos.Dtos.*;
import com.br.femmcode.femmcode.services.ComunidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")   // porta do seu front (Vite)
public class ComunidadeController {

    private final ComunidadeService service;

    public ComunidadeController(ComunidadeService service) {
        this.service = service;
    }

    @GetMapping("/comunidades")
    public List<ComunidadeResponse> listar(@RequestParam(required = false) String busca,
                                           @RequestParam(required = false) String filtro,
                                           @RequestParam(required = false) String usuarioId) {
        return service.listar(busca, filtro, usuarioId);
    }

    @PostMapping("/comunidades")
    @ResponseStatus(HttpStatus.CREATED)
    public ComunidadeResponse criar(@Valid @RequestBody ComunidadeRequest r) {
        return service.criar(r);
    }

    @PostMapping("/comunidades/{id}/entrar")
    public ComunidadeResponse entrar(@PathVariable String id, @RequestParam String usuarioId) {
        return service.entrar(id, usuarioId);
    }

    @GetMapping("/comunidades/{id}/postagens")
    public List<PostagemResponse> postagensDaComunidade(@PathVariable String id,
                                                        @RequestParam(required = false) String filtro,
                                                        @RequestParam(required = false) String usuarioId) {
        return service.listarPostagens(id, filtro, usuarioId);
    }

    @PostMapping("/comunidades/{id}/postagens")
    @ResponseStatus(HttpStatus.CREATED)
    public PostagemResponse postar(@PathVariable String id, @Valid @RequestBody PostagemRequest r) {
        return service.postar(id, r);
    }

    @GetMapping("/postagens")   // feed geral
    public List<PostagemResponse> feed(@RequestParam(required = false) String filtro,
                                       @RequestParam(required = false) String usuarioId) {
        return service.listarPostagens(null, filtro, usuarioId);
    }

    @PostMapping("/postagens/{id}/curtir")
    public PostagemResponse curtir(@PathVariable String id, @RequestParam String usuarioId) {
        return service.curtir(id, usuarioId);
    }
}