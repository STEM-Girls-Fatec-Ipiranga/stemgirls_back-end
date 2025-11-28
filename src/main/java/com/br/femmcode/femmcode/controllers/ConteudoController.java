package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.models.Conteudo;
import com.br.femmcode.femmcode.services.ConteudoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conteudos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ConteudoController {

    private final ConteudoService service;

    @PostMapping
    public Conteudo salvar(@RequestBody Conteudo c) {
        return service.salvar(c);
    }

    @GetMapping
    public List<Conteudo> listar() {
        return service.listar();
    }
}
