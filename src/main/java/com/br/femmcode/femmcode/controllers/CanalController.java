package com.br.femmcode.femmcode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.br.femmcode.femmcode.models.Canal;
import com.br.femmcode.femmcode.models.Video;
import com.br.femmcode.femmcode.repositories.CanalRepository;
import com.br.femmcode.femmcode.repositories.VideoRepository;
import com.br.femmcode.femmcode.services.CanalService;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/canais")
@CrossOrigin(originPatterns = {"http://localhost:5173", "http://localhost:3000"})
@RequiredArgsConstructor
public class CanalController {
    @Autowired
    private CanalService canalService;

    @Autowired
    private VideoRepository videoRepository;

    private static final String UPLOAD_DIR = "uploads/canais/";

    @GetMapping
    public List<Canal> listarTodos() {
        return canalService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Canal> buscarPorId(@PathVariable String id) {
        return canalService.buscarPorId(id);
    }


    @PostMapping(consumes = {"multipart/form-data"})

    public Canal criar(
        @RequestParam("nome") String nome,
        @RequestParam("descricao") String descricao,
        @RequestParam(value = "banner", required = false) MultipartFile banner,
        @RequestParam(value = "fotoPerfil", required = false) MultipartFile fotoPerfil,
        @RequestParam("owner") String owner) throws IOException {

        String uploadDir = "uploads/canais/";
        File directory = new File(uploadDir);
        if (!directory.exists()) directory.mkdirs();

        Canal canal = new Canal();
        canal.setNome(nome);
        canal.setDescricao(descricao);
        canal.setOwner(owner);

        if (banner != null && !banner.isEmpty()) {
            String bannerName = java.util.UUID.randomUUID() + "_" + banner.getOriginalFilename();
            Path bannerPath = Paths.get(uploadDir + bannerName);
            Files.write(bannerPath, banner.getBytes());
            canal.setBanner("http://localhost:8080/uploads/canais/" + bannerName);
        }

        if (fotoPerfil != null && !fotoPerfil.isEmpty()) {
            String fotoName = java.util.UUID.randomUUID() + "_" + fotoPerfil.getOriginalFilename();
            Path fotoPath = Paths.get(uploadDir + fotoName);
            Files.write(fotoPath, fotoPerfil.getBytes());
            canal.setFotoPerfil("http://localhost:8080/uploads/canais/" + fotoName);
        }

        return canalService.criar(canal);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        canalService.deletar(id);
    }

    @GetMapping("/test-mongo")
public Canal testMongo() {
    Canal c = new Canal();
    c.setNome("Teste");
    c.setDescricao("Teste Mongo");
    c.setOwner("test");
    return canalService.criar(c);
}
@GetMapping("/{canalId}/videos")
public ResponseEntity<List<Video>> listarVideosDoCanal(@PathVariable String canalId) {
    List<Video> videos = videoRepository.findByCanalId(canalId);
    return ResponseEntity.ok(videos);
}


}

