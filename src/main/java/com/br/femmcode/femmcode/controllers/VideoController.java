package com.br.femmcode.femmcode.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import com.br.femmcode.femmcode.models.Video;
import com.br.femmcode.femmcode.services.VideoService;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(originPatterns = {"http://localhost:5173", "http://localhost:3000"})
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    public List<Video> listarTodos() {
        return videoService.listarTodos();
    }

    @GetMapping("/canal/{canalId}")
    public List<Video> listarPorCanal(@PathVariable String canalId) {
        return videoService.listarPorCanal(canalId);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public Video uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam(value = "desc", required = false) String desc,
            @RequestParam("canalId") String canalId,
            @RequestParam("owner") String owner) throws IOException {

        // 1️⃣ Define onde salvar os arquivos (cria pasta se não existir)
        String uploadDir = "uploads/videos/";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 2️⃣ Gera nome único para o arquivo
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir + fileName);

        // 3️⃣ Salva o arquivo no disco
        Files.write(path, file.getBytes());

        // 4️⃣ Cria o objeto Video e preenche os dados
        Video video = new Video();
        video.setTitle(title);
        video.setDesc(desc);
        video.setCanalId(canalId);
        video.setOwner(owner);
        video.setUrl("http://localhost:8080/uploads/videos/" + fileName);
        video.setFileName(fileName);

        // 5️⃣ Salva no MongoDB
        return videoService.criar(video);
    }

     @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        // Buscar o vídeo antes de apagar
        Optional<Video> opt = videoService.buscarPorId(id);
        if (opt.isPresent()) {
            Video video = opt.get();

            // Se o vídeo tiver um arquivo físico salvo, tenta deletar
            try {
                if (video.getUrl() != null && video.getUrl().startsWith("/uploads/")) {
                    String caminhoArquivo = System.getProperty("user.dir") + "/src/main/resources/static" + video.getUrl();
                    File arquivo = new File(caminhoArquivo);
                    if (arquivo.exists()) {
                        arquivo.delete();
                        System.out.println("🗑️ Arquivo deletado: " + caminhoArquivo);
                    }
                }
            } catch (Exception e) {
                System.err.println("⚠️ Erro ao excluir arquivo físico: " + e.getMessage());
            }

            // Apaga o registro do banco
            videoService.deletar(id);
        }
    }
}
