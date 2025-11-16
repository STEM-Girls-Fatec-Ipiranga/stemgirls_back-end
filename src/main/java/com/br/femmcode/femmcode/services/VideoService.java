package com.br.femmcode.femmcode.services;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import com.br.femmcode.femmcode.models.Video;
import com.br.femmcode.femmcode.repositories.VideoRepository;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public List<Video> listarTodos() {
        return videoRepository.findAll();
    }

    public List<Video> getVideosByCanal(String canalId) {
        return videoRepository.findByCanalId(canalId);
    }

    public Optional<Video> buscarPorId(String id) {
        return videoRepository.findById(id);
    }

    public Video buscarPorOriginalName(String originalName) {
        return videoRepository.findByOriginalName(originalName);
    }

    public Video criar(Video video) {
        return videoRepository.save(video);
    }

    public void deletar(String id) {
        videoRepository.deleteById(id);
    }
}
