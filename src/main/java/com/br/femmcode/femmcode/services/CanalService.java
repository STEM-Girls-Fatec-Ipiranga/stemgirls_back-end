package com.br.femmcode.femmcode.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.femmcode.femmcode.models.Canal;
import com.br.femmcode.femmcode.repositories.CanalRepository;


@Service
public class CanalService {

    private final CanalRepository canalRepository;

    public CanalService(CanalRepository canalRepository) {
        this.canalRepository = canalRepository;
    }


    public List<Canal> listarTodos() {
        return canalRepository.findAll();
    }

    public Optional<Canal> buscarPorId(String id) {
        return canalRepository.findById(id);
    }

    public Canal criar(Canal canal) {
        return canalRepository.save(canal);
    }

    public void deletar(String id) {
        canalRepository.deleteById(id);
    }
}

