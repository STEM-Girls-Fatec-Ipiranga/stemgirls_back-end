package com.br.femmcode.femmcode.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.br.femmcode.femmcode.repositories.ComunidadeRepository;
import com.br.femmcode.femmcode.services.ComunidadeService;
import com.br.femmcode.femmcode.dtos.*;
import com.br.femmcode.femmcode.models.Comunidade;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComunidadeServiceImpl implements ComunidadeService {

    private final ComunidadeRepository comunidadeRepository;

    public ComunidadeServiceImpl(ComunidadeRepository comunidadeRepository) {
        this.comunidadeRepository = comunidadeRepository;
    }

    @Override
    public ComunidadeRespostaDTO criar(CriarComunidadeDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da comunidade é obrigatório");
        }
        if (comunidadeRepository.existsByNome(dto.getNome())) {
            throw new IllegalArgumentException("Já existe uma comunidade com este nome");
        }

        String criador = dto.getNomeUsuarioCriador();
        Comunidade comunidade = new Comunidade(dto.getNome(), dto.getDescricao(), criador);

        comunidade.setCriadaEm(Instant.now());
        comunidade.setAtualizadaEm(Instant.now());

        Comunidade salva = comunidadeRepository.save(comunidade);
        return mapearParaResposta(salva);
    }

    @Override
    public List<ComunidadeRespostaDTO> listarTodas() {
        return comunidadeRepository.findAll().stream()
                .map(this::mapearParaResposta)
                .collect(Collectors.toList());
    }

    @Override
    public ComunidadeRespostaDTO buscarPorId(String id) {
        Comunidade c = comunidadeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Comunidade não encontrada com id: " + id));
        return mapearParaResposta(c);
    }

    @Override
    public ComunidadeRespostaDTO atualizar(String id, AtualizarComunidadeDTO dto) {
        Comunidade c = comunidadeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Comunidade não encontrada com id: " + id));

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            c.setNome(dto.getNome());
        }
        if (dto.getDescricao() != null) {
            c.setDescricao(dto.getDescricao());
        }

        c.setAtualizadaEm(Instant.now());

        Comunidade atualizada = comunidadeRepository.save(c);
        return mapearParaResposta(atualizada);
    }

    @Override
    public void deletar(String id) {
        if (!comunidadeRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Comunidade não encontrada com id: " + id);
        }
        comunidadeRepository.deleteById(id);
    }

    @Override
    public ComunidadeRespostaDTO adicionarPost(String idComunidade, String idPost) {
        Comunidade c = comunidadeRepository.findById(idComunidade)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Comunidade não encontrada com id: " + idComunidade));

        if (!c.getIdsPosts().contains(idPost)) {
            c.getIdsPosts().add(idPost);
            c.setAtualizadaEm(Instant.now());
            comunidadeRepository.save(c);
        }

        return mapearParaResposta(c);
    }

    @Override
    public ComunidadeRespostaDTO removerPost(String idComunidade, String idPost) {
        Comunidade c = comunidadeRepository.findById(idComunidade)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Comunidade não encontrada com id: " + idComunidade));

        if (c.getIdsPosts().remove(idPost)) {
            c.setAtualizadaEm(Instant.now());
            comunidadeRepository.save(c);
        }

        return mapearParaResposta(c);
    }

    private ComunidadeRespostaDTO mapearParaResposta(Comunidade c) {
        ComunidadeRespostaDTO r = new ComunidadeRespostaDTO();
        r.setId(c.getId());
        r.setNome(c.getNome());
        r.setDescricao(c.getDescricao());
        r.setNomeUsuarioCriador(c.getNomeUsuarioCriador());
        r.setCriadaEm(c.getCriadaEm());
        r.setAtualizadaEm(c.getAtualizadaEm());
        r.setIdsPosts(c.getIdsPosts());
        return r;
    }
}
