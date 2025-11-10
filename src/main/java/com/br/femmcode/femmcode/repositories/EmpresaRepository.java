package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Empresa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {
    Optional<Empresa> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsBySenha(String senha);
    boolean existsByCnpj(String cnpj);
}
