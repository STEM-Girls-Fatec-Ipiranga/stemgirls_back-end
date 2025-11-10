package com.br.femmcode.femmcode.controllers;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.br.femmcode.femmcode.models.Canal;
import com.br.femmcode.femmcode.services.CanalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/canais")
@CrossOrigin(originPatterns = {"http://localhost:5173", "http://localhost:3000"})
@RequiredArgsConstructor
public class CanalController {

    private final CanalService canalService;

    @GetMapping
    public List<Canal> listarTodos() {
        return canalService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Canal> buscarPorId(@PathVariable String id) {
        return canalService.buscarPorId(id);
    }

    @PostMapping
    public Canal criar(@RequestBody Canal canal) {
        return canalService.criar(canal);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        canalService.deletar(id);
    }
}


