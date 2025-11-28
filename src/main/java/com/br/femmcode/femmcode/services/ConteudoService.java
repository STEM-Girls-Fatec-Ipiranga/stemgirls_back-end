package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.models.Conteudo;
import com.br.femmcode.femmcode.repositories.ConteudoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConteudoService {

    private final ConteudoRepository repository;

    public Conteudo salvar(Conteudo c) {
        return repository.save(c);
    }

    public List<Conteudo> listar() {
        return repository.findAll();
    }
}

