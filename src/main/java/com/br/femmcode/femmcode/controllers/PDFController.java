package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.dtos.UsuarioDTO;
import com.br.femmcode.femmcode.services.PDFService;
import org.apache.coyote.Response;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificado")
@CrossOrigin(origins = "http://localhost:5173")
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @GetMapping("/criar/{usuario}/{evento}/{data}")
    public ResponseEntity<String> criarPDF(@PathVariable String usuario, String evento, String data){
        pdfService.criarPDF(usuario, evento, data);
        String msg = "PDF salvo com sucesso";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/salvar/{usuario}/{evento}/{data}")
    public ResponseEntity<byte[]> salvarPDF(@PathVariable("usuario") String usuario,
                                            @PathVariable("evento") String evento,
                                            @PathVariable("data") String data){
        byte[] pdf = pdfService.criarPDF(usuario, evento, data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificado.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
