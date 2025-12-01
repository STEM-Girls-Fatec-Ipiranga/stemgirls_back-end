package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.models.VideoMini;
import com.br.femmcode.femmcode.repositories.VideoMiniRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class VideoMiniService {

    private final VideoMiniRepository videoMiniRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public VideoMini upload(MultipartFile file, String titulo, String descricao) throws Exception {

        File dir = new File(uploadPath);
        if (!dir.exists()) dir.mkdirs();

        String filePath = uploadPath + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Path.of(filePath);
        Files.write(path, file.getBytes());

        VideoMini videoMini = new VideoMini();
        videoMini.setTitulo(titulo);
        videoMini.setDescricao(descricao);
        videoMini.setCaminhoArquivo(filePath);
        videoMini.setContentType(file.getContentType());
        videoMini.setTamanho(file.getSize());

        return videoMiniRepository.save(videoMini);
    }

    public static String salvarArquivo(MultipartFile file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvarArquivo'");
    }

    public static void save(VideoMini video) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
