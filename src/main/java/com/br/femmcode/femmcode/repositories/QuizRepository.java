package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepository extends MongoRepository<Quiz, String> {
}

