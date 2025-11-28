package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.dtos.InscricaoDTO;
import com.br.femmcode.femmcode.models.Inscricao;
import com.br.femmcode.femmcode.services.InscricaoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/inscricoes")
@CrossOrigin(
    originPatterns = "*",
    allowedHeaders = "*",
    allowCredentials = "true"
)
public class InscricaoController {

    private final InscricaoService service;

    public InscricaoController(InscricaoService service) {
        this.service = service;
    }

    @PostMapping
    public Inscricao criar(@RequestBody InscricaoDTO dto) {
        return service.criarInscricao(dto);
    }

    @GetMapping
    public List<Inscricao> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/evento/{eventoId}")
    public List<Inscricao> listarPorEvento(@PathVariable String eventoId) {
        return service.listarPorEvento(eventoId);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }

    // 🔥 NOVO ENDPOINT: DOWNLOAD CSV
    @GetMapping("/evento/{eventoId}/download")
    public void baixarInscricoesPorEvento(
            @PathVariable String eventoId,
            HttpServletResponse response) throws IOException {

        List<Inscricao> inscricoes = service.listarPorEvento(eventoId);

        response.setContentType("text/csv");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=inscricoes_evento_" + eventoId + ".csv"
        );

        PrintWriter writer = response.getWriter();
        writer.println("Nome,CPF,Email,Telefone,Instituicao");

        for (Inscricao i : inscricoes) {
            writer.println(
                i.getNome() + "," +
                i.getCpf() + "," +
                i.getEmail() + "," +
                i.getTelefone() + "," +
                i.getInstituicao()
            );
        }

        writer.flush();
        writer.close();
    }
}
