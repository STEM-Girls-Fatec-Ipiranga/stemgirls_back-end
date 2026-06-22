package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.controllers.CloudinaryController;
import com.br.femmcode.femmcode.dtos.EventoDTO;
import com.br.femmcode.femmcode.dtos.InscricaoDTO;
import com.br.femmcode.femmcode.models.Evento;
import com.br.femmcode.femmcode.models.Inscricao;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.repositories.EventoRepository;
import com.br.femmcode.femmcode.repositories.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.opencsv.CSVWriter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Evento criarEvento(EventoDTO dto, MultipartFile imagem) throws IOException {
        Evento novoEvento = new Evento();

        String urlImagem = cloudinaryService.uploadFile(imagem);

        novoEvento.setOrganizador(dto.organizador());

        novoEvento.setTitulo(dto.titulo());
        novoEvento.setEndereco(dto.endereco());
        novoEvento.setDescricao(dto.descricao());

        novoEvento.setData(dto.data());
        novoEvento.setHora(dto.hora());
        novoEvento.setModalidade(dto.modalidade());

        novoEvento.setImagem(urlImagem);
        novoEvento.setLinkInscricao(dto.linkInscricao());

        return eventoRepository.save(novoEvento);
    }

    public Evento atualizarEvento(String eventoId, EventoDTO dto) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new UsernameNotFoundException("Evento não existe!"));

        updateIfNotNull(dto.titulo(), evento::setTitulo);
        updateIfNotNull(dto.endereco(), evento::setEndereco);
        updateIfNotNull(dto.descricao(), evento::setDescricao);
        updateIfNotNull(dto.data(), evento::setData);
        updateIfNotNull(dto.hora(), evento::setHora);
        updateIfNotNull(dto.organizador(), evento::setOrganizador);
        updateIfNotNull(dto.imagem(), evento::setImagem);
        updateIfNotNull(dto.modalidade(), evento::setModalidade);

        return eventoRepository.save(evento);
    }

    private <T> void updateIfNotNull(T value, Consumer<T> setter){
        if(value != null){
            setter.accept(value);
        }
    }

    public Optional<List<Evento>> pesquisarEvento(String titulo) {
        return eventoRepository.findByTitulo(titulo);
    }

    public Evento encontrarEvento(String id){
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado!"));
    }

    public Optional<List<Evento>> buscarMeusEventos(String organizadorId){
        return eventoRepository.findByOrganizador(organizadorId);
    }

    public Inscricao adicionarInscricao(String eventoId, InscricaoDTO dto) {
        Inscricao insc = inscricaoService.encontrarInscricao(dto.participanteId(), eventoId);
        if(insc!=null){
            return insc;
        }else{
            return inscricaoService.criarInscricao(eventoId, dto);
        }
        //return insc.orElseGet(() -> inscricaoService.criarInscricao(eventoId, dto));
    }

    public void exportarInscrcicoesCSV(Writer writer, List<Inscricao> listaInscricoes) throws IOException {
        try(CSVWriter csvWriter = new CSVWriter(
                writer,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)){

            String[] header = {"Id","Nome","Email","CPF","Telefone","Instituição","Evento","Data da Inscrição"};
            csvWriter.writeNext(header);

            for(Inscricao insc : listaInscricoes){
                String[] body = {
                        String.valueOf(insc.getId()),
                        insc.getParticipante().getNome(),
                        insc.getParticipante().getEmail(),
                        insc.getParticipante().getCpf(),
                        insc.getParticipante().getTelefone(),
                        insc.getInstituicao(),
                        insc.getEvento().getTitulo(),
                        String.valueOf(insc.getDataInscricao())
                };
                csvWriter.writeNext(body);
            }
        }
    }

    public Optional<List<Inscricao>> listarParticipantes(String eventoId){
        return inscricaoService.listarInscricoesEvento(eventoId);
    }

    public List<Evento> listarEventos(){
        return eventoRepository.findAll();
    }

    public String excluirEvento(String id) {
        eventoRepository.deleteById(id);
        return "Evento deletado com sucesso!";
    }

}
