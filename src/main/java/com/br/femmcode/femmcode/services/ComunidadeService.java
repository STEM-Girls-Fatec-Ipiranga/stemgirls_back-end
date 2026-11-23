package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.dtos.Dtos.*;
import com.br.femmcode.femmcode.models.Comunidade;
import com.br.femmcode.femmcode.models.Postagem;
import com.br.femmcode.femmcode.repositories.ComunidadeRepository;
import com.br.femmcode.femmcode.repositories.PostagemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ComunidadeService {

    private static final Sort RECENTES = Sort.by(Sort.Direction.DESC, "dataCriacao");

    private final ComunidadeRepository comunidades;
    private final PostagemRepository postagens;

    public ComunidadeService(ComunidadeRepository comunidades, PostagemRepository postagens) {
        this.comunidades = comunidades;
        this.postagens = postagens;
    }

    // filtro: "minhas" | "participo" | (vazio = todas, com busca opcional)
    public List<ComunidadeResponse> listar(String busca, String filtro, String usuarioId) {
        List<Comunidade> lista;
        if ("minhas".equals(filtro)) lista = comunidades.findByDonoId(usuarioId);
        else if ("participo".equals(filtro)) lista = comunidades.findByMembrosContainingAndDonoIdNot(usuarioId, usuarioId);
        else if (busca != null && !busca.isBlank())
            lista = comunidades.findByNomeContainingIgnoreCaseOrNomeUsuarioContainingIgnoreCase(busca, busca);
        else lista = comunidades.findAll();
        return lista.stream().map(c -> toDto(c, usuarioId)).toList();
    }

    public ComunidadeResponse criar(ComunidadeRequest r) {
        String handle = r.nomeUsuario().trim();
        if (!handle.startsWith("@")) handle = "@" + handle;
        if (comunidades.existsByNomeUsuarioIgnoreCase(handle))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome de usuário já em uso");

        Comunidade c = new Comunidade();
        c.setNome(r.nome().trim());
        c.setNomeUsuario(handle);
        c.setDescricao(r.descricao());
        c.setImagem(r.imagem());
        c.setDonoId(r.usuarioId());
        c.getMembros().add(r.usuarioId());   // o dono já é membro
        return toDto(comunidades.save(c), r.usuarioId());
    }

    public ComunidadeResponse entrar(String comunidadeId, String usuarioId) {
        Comunidade c = buscar(comunidadeId);
        c.getMembros().add(usuarioId);
        return toDto(comunidades.save(c), usuarioId);
    }

    public PostagemResponse postar(String comunidadeId, PostagemRequest r) {
        Comunidade c = buscar(comunidadeId);
        if (!c.getMembros().contains(r.usuarioId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Entre na comunidade para publicar");

        Postagem p = new Postagem();
        p.setComunidadeId(comunidadeId);
        p.setAutorId(r.usuarioId());
        p.setTitulo(r.titulo().trim());
        p.setConteudo(r.conteudo().trim());
        p.setImagem(r.imagem());
        return toDto(postagens.save(p), c, r.usuarioId());
    }

    // comunidadeId nulo = feed geral | filtro: "recentes" (padrão) | "alta"
    public List<PostagemResponse> listarPostagens(String comunidadeId, String filtro, String usuarioId) {
        List<Postagem> lista = comunidadeId == null
                ? postagens.findAll(RECENTES)
                : postagens.findByComunidadeId(comunidadeId, RECENTES);

        if ("alta".equals(filtro))
            lista = lista.stream()
                    .sorted(Comparator.comparingInt((Postagem p) -> p.getCurtidas().size()).reversed())
                    .toList();

        Map<String, Comunidade> mapa = comunidades
                .findAllById(lista.stream().map(Postagem::getComunidadeId).collect(Collectors.toSet()))
                .stream().collect(Collectors.toMap(Comunidade::getId, c -> c));

        return lista.stream().map(p -> toDto(p, mapa.get(p.getComunidadeId()), usuarioId)).toList();
    }

    public PostagemResponse curtir(String postagemId, String usuarioId) {
        Postagem p = postagens.findById(postagemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não encontrada"));
        if (!p.getCurtidas().remove(usuarioId)) p.getCurtidas().add(usuarioId);   // alterna
        return toDto(postagens.save(p), buscar(p.getComunidadeId()), usuarioId);
    }

    // ---------- auxiliares ----------
    private Comunidade buscar(String id) {
        return comunidades.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comunidade não encontrada"));
    }

    private ComunidadeResponse toDto(Comunidade c, String uid) {
        return new ComunidadeResponse(c.getId(), c.getNome(), c.getNomeUsuario(), c.getDescricao(), c.getImagem(),
                c.getMembros().size(), c.getDonoId().equals(uid), c.getMembros().contains(uid));
    }

    private PostagemResponse toDto(Postagem p, Comunidade c, String uid) {
        return new PostagemResponse(p.getId(), c.getId(), c.getNome(), c.getImagem(),
                p.getTitulo(), p.getConteudo(), p.getImagem(), p.getDataCriacao(),
                p.getCurtidas().size(), p.getCurtidas().contains(uid), c.getMembros().contains(uid));
    }
}