package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.MiniMentes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MiniMentesRepository
        extends MongoRepository<MiniMentes, String> {
}

