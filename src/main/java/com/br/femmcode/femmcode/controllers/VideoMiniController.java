package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.models.VideoMini;
import com.br.femmcode.femmcode.services.VideoMiniService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VideoMiniController {

    private final VideoMiniService service;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadVideo(
        @RequestParam("file") MultipartFile file,
        @RequestParam("titulo") String titulo,
        @RequestParam("descricao") String descricao
) {
    try {
        String url = VideoMiniService.salvarArquivo(file);

        VideoMini video = new VideoMini();
        video.setTitulo(titulo);
        video.setDescricao(descricao);
        video.setId(url);

        VideoMiniService.save(video);

        return ResponseEntity.ok("Upload realizado com sucesso!");

    } catch (Exception e) {
        return ResponseEntity.status(500).body("Erro ao enviar o vídeo: " + e.getMessage());
    }
}

}

