package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.dtos.InscricaoDTO;
import com.br.femmcode.femmcode.models.Inscricao;
import com.br.femmcode.femmcode.repositories.InscricaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoService {

    private final InscricaoRepository repository;

    public InscricaoService(InscricaoRepository repository) {
        this.repository = repository;
    }

    public Inscricao criarInscricao(InscricaoDTO dto) {
        Inscricao i = new Inscricao();
        i.setEventoId(dto.getEventoId());
        i.setNome(dto.getNome());
        i.setCpf(dto.getCpf());
        i.setEmail(dto.getEmail());
        i.setTelefone(dto.getTelefone());
        i.setInstituicao(dto.getInstituicao());

        return repository.save(i);
    }

    public List<Inscricao> listarTodas() {
        return repository.findAll();
    }

    public List<Inscricao> listarPorEvento(String eventoId) {
        return repository.findByEventoId(eventoId);
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }
}
