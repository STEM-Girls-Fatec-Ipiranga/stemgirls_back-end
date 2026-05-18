package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.services.PDFService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificado")
@CrossOrigin(origins = "http://localhost:5173")
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @GetMapping("/criar")
    public ResponseEntity<String> criarPDF(){
        pdfService.criarPDF();
        String msg = "PDF salvo com sucesso";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
