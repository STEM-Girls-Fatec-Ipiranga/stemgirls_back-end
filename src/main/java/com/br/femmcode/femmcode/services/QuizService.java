package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.models.Quiz;
import com.br.femmcode.femmcode.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    public Quiz salvar(Quiz q) {
        return quizRepository.save(q);
    }

    public List<Quiz> listar() {
        return quizRepository.findAll();
    }
}
