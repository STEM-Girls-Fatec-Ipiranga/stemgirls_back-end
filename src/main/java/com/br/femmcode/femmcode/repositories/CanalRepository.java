package com.br.femmcode.femmcode.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.br.femmcode.femmcode.models.Canal;

@Repository
public interface CanalRepository extends MongoRepository<Canal, String> {
}


