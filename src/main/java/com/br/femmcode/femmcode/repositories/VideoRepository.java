package com.br.femmcode.femmcode.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.br.femmcode.femmcode.models.Video;
import java.util.List;

@Repository
public interface VideoRepository extends MongoRepository<Video, String> {
    List<Video> findByCanalId(String canalId);
}
