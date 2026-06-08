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
    private static final String UPLOAD_DIR = "uploads/videos/";
    private static final String BASE_URL = "http://localhost:8080";

    @GetMapping
    public List<Video> listarTodos() {
        return videoService.listarTodos();
    }

    @GetMapping("/canal/{canalId}")
    public List<Video> listarPorCanal(@PathVariable String canalId) {
        return videoService.getVideosByCanal(canalId);
    }

    @PostMapping(consumes = {"multipart/form-data"})
        public Video uploadVideo(
        @RequestParam("link") String link,
        @RequestParam("title") String title,
        @RequestParam(value = "desc", required = false) String desc,
        @RequestParam("canalId") String canalId,
        @RequestParam("owner") String owner,
        @RequestParam(value = "thumb", required = false) MultipartFile thumb
    ) throws IOException {

        // cria pasta se necessário
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Não foi possível criar diretório de upload: " + UPLOAD_DIR);
        }


        Video video = new Video();
        video.setTitle(title);
        video.setDesc(desc);
        video.setCanalId(canalId);
        video.setOwner(owner);
        video.setCreatedAt(System.currentTimeMillis());
        video.setUrl(link);

        // thumbnail opcional (salva na mesma pasta)
        if (thumb != null && !thumb.isEmpty()) {
            String thumbOriginal = thumb.getOriginalFilename() != null
                    ? thumb.getOriginalFilename().replaceAll("\\s+", "_")
                    : "thumb";
            String thumbName = System.currentTimeMillis() + "_thumb_" + thumbOriginal;
            Path thumbPath = Paths.get(UPLOAD_DIR, thumbName);
            Files.write(thumbPath, thumb.getBytes());
            video.setThumbnail(BASE_URL + "/uploads/videos/" + thumbName);
        }

        return videoService.criar(video);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        Optional<Video> opt = videoService.buscarPorId(id);
        if (opt.isPresent()) {
            Video video = opt.get();

            // apaga arquivo do vídeo
            try {
                if (video.getFileName() != null) {
                    Path p = Paths.get(UPLOAD_DIR, video.getFileName());
                    Files.deleteIfExists(p);
                }
            } catch (Exception e) {
                System.err.println("Erro ao deletar arquivo de vídeo: " + e.getMessage());
            }

            // apaga thumbnail
            try {
                if (video.getThumbnail() != null) {
                    String thumbUrl = video.getThumbnail();
                    String name = thumbUrl.contains("/") ? thumbUrl.substring(thumbUrl.lastIndexOf("/") + 1) : thumbUrl;
                    Path tp = Paths.get(UPLOAD_DIR, name);
                    Files.deleteIfExists(tp);
                }
            } catch (Exception e) {
                System.err.println("Erro ao deletar thumb: " + e.getMessage());
            }

            videoService.deletar(id);
        }
    }
}
