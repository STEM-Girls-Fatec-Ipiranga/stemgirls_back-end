package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.dtos.InscricaoDTO;
import com.br.femmcode.femmcode.models.Evento;
import com.br.femmcode.femmcode.models.Inscricao;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.repositories.EventoRepository;
import com.br.femmcode.femmcode.repositories.InscricaoRepository;
import com.br.femmcode.femmcode.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EventoRepository eventoRepository;

    public Inscricao criarInscricao(String eventoId, InscricaoDTO dto) {
        Usuario participante = usuarioService.encontrarUsuario(dto.participanteId());
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado!"));

        Inscricao novaInscricao = new Inscricao();

        novaInscricao.setParticipante(participante);
        novaInscricao.setEvento(evento);

        novaInscricao.setInstituicao(dto.instituicao());

        return inscricaoRepository.save(novaInscricao);
    }

    public Inscricao encontrarInscricao(String participanteId, String eventoId){
        Optional<Inscricao> insc = inscricaoRepository.findByParticipanteAndEvento(participanteId, eventoId);
        return insc.orElse(null);
    }

    public Optional<List<Inscricao>> buscarPorParticipante(String participanteId){
        return inscricaoRepository.findByParticipante(participanteId);
    }

    public Optional<List<Inscricao>> listarInscricoesEvento(String eventoId){
        return inscricaoRepository.findByEvento(eventoId);
    }

    public String excluirInscricao(String id) {
        inscricaoRepository.deleteById(id);
        return "Inscrição excluída com sucesso!";
    }
}
