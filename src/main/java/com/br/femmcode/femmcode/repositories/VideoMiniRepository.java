package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.VideoMini;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoMiniRepository extends MongoRepository<VideoMini, String> {
}

