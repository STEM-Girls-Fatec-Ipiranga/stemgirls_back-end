package com.br.femmcode.femmcode.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.br.femmcode.femmcode.repositories")
public class MongoConfig {
}
