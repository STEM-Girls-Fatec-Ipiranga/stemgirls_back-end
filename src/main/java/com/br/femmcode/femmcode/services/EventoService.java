package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.models.Evento;
import com.br.femmcode.femmcode.models.Inscricao;
import com.br.femmcode.femmcode.repositories.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EventoService {

    private final EventoRepository repo;

    public EventoService(EventoRepository repo) {
        this.repo = repo;
    }

    // retorna eventos persistidos no banco
    public List<Evento> listarEventosPersistidos() {
        return repo.findAll();
    }

    // cria um novo evento
    public Evento criarEvento(Evento evento) {
        return repo.save(evento);
    }

    public void excluirEvento(String id) {
        repo.deleteById(id);
    }

    public Optional<Evento> buscarPorId(String id) {
        return repo.findById(id);
    }

    public Evento adicionarInscricao(String eventoId, Inscricao inscricao) throws IllegalArgumentException {
        Evento evt = repo.findById(eventoId).orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));
        // valida CPF simples: já validado no front, mas podemos checar formato/duplicidade
        boolean duplicate = evt.getParticipantes().stream()
                .anyMatch(p -> p.getCpf().replaceAll("\\D","").equals(inscricao.getCpf().replaceAll("\\D","")));
        if (duplicate) {
            throw new IllegalArgumentException("CPF já inscrito neste evento");
        }
        evt.getParticipantes().add(inscricao);
        return repo.save(evt);
    }

    // --- helper para eventos fixos (não persistidos) ---
    public List<Evento> eventosFixos() {
        List<Evento> fixos = new ArrayList<>();

        Evento e1 = new Evento();
        e1.setId("fixo-1");
        e1.setTitulo("Reunião meninas digitais");
        e1.setData("26/06/2025");
        e1.setHora("19:00");
        e1.setTipo("ao-vivo");
        e1.setLocal("São Paulo");
        e1.setDescricao("Reunião para trocar ideias e conhecimentos sobre o projeto buscando o aprimoramento.");
        e1.setImagem("/assets/img/mulheres-tecnologia.jpg");
        fixos.add(e1);

        Evento e2 = new Evento();
        e2.setId("fixo-2");
        e2.setTitulo("Palestra tecnologia inclusiva");
        e2.setData("28/06/2025");
        e2.setHora("15:00");
        e2.setTipo("presencial");
        e2.setLocal("Rio de Janeiro");
        e2.setDescricao("Discussão sobre inclusão de mulheres no mercado de tecnologia.");
        e2.setImagem("/assets/img/mulheres-tecnologia.jpg");
        fixos.add(e2);

        Evento e3 = new Evento();
        e3.setId("fixo-3");
        e3.setTitulo("Workshop React para iniciantes");
        e3.setData("05/07/2025");
        e3.setHora("18:00");
        e3.setTipo("remoto");
        e3.setLocal("Online");
        e3.setDescricao("Oficina prática para quem deseja aprender os fundamentos do React.");
        e3.setImagem("/assets/img/mulheres-tecnologia.jpg");
        fixos.add(e3);

        Evento e4 = new Evento();
        e4.setId("fixo-4");
        e4.setTitulo("Encontro Mulheres na TI");
        e4.setData("10/07/2025");
        e4.setHora("14:00");
        e4.setTipo("presencial");
        e4.setLocal("Belo Horizonte");
        e4.setDescricao("Evento presencial para networking e troca de experiências.");
        e4.setImagem("/assets/img/mulheres-tecnologia.jpg");
        fixos.add(e4);

        Evento e5 = new Evento();
        e5.setId("fixo-5");
        e5.setTitulo("Mentoria carreira em tecnologia");
        e5.setData("15/07/2025");
        e5.setHora("20:00");
        e5.setTipo("remoto");
        e5.setLocal("Online");
        e5.setDescricao("Sessão de mentoria com profissionais experientes do mercado.");
        e5.setImagem("/assets/img/mulheres-tecnologia.jpg");
        fixos.add(e5);

        Evento e6 = new Evento();
        e6.setId("fixo-6");
        e6.setTitulo("Hackathon inclusão digital");
        e6.setData("20/07/2025");
        e6.setHora("09:00");
        e6.setTipo("presencial");
        e6.setLocal("Curitiba");
        e6.setDescricao("Competição de programação com foco em soluções inclusivas.");
        e6.setImagem("/assets/img/mulheres-tecnologia.jpg");
        fixos.add(e6);

        return fixos;
    }
    public Optional<Evento> atualizarEvento(String id, Evento novo) {
    return repo.findById(id).map(existente -> {

        existente.setOrganizadorTipo(novo.getOrganizadorTipo());
        existente.setTitulo(novo.getTitulo());
        existente.setData(novo.getData());
        existente.setHora(novo.getHora());
        existente.setTipo(novo.getTipo());
        existente.setLocal(novo.getLocal());
        existente.setDescricao(novo.getDescricao());
        existente.setImagem(novo.getImagem());
        existente.setLinkInscricao(novo.getLinkInscricao());
        existente.setEnderecoCompleto(novo.getEnderecoCompleto());

        return repo.save(existente);
    });
}
}
