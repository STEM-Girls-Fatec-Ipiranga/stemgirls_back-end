package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.models.Quiz;
import com.br.femmcode.femmcode.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuizController {

    private final QuizService service;

    @PostMapping
    public Quiz salvar(@RequestBody Quiz q) {
        return service.salvar(q);
    }

    @GetMapping
    public List<Quiz> listar() {
        return service.listar();
    }
}

