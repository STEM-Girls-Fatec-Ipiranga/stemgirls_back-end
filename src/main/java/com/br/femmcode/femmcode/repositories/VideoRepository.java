package com.br.femmcode.femmcode.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.br.femmcode.femmcode.models.Video;

public interface VideoRepository extends MongoRepository<Video, String> {
    List<Video> findByCanalId(String canalId);
    Video findByOriginalName(String originalName);
}
