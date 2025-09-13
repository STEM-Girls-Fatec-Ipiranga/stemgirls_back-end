package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    /**
     * Busca um usuário pelo seu endereço de e-mail.
     * Retorna um Optional para tratar de forma segura o caso de o usuário não ser encontrado.
     *
     * @param email O e-mail do usuário a ser buscado.
     * @return um Optional contendo o Usuario se encontrado, ou um Optional vazio caso contrário.
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Verifica de forma eficiente se um usuário com o e-mail especificado já existe.
     *
     * @param email O e-mail a ser verificado.
     * @return true se o e-mail já estiver em uso, false caso contrário.
     */
    Boolean existsByEmail(String email);

    /**
     * Busca um usuário pelo token de redefinição de senha.
     * Será usado na funcionalidade "Esqueci a Senha".
     *
     * @param token O token de redefinição de senha.
     * @return um Optional contendo o Usuario se encontrado.
     */
    Optional<Usuario> findByPasswordResetToken(String token);

    // O método findByEmailAndSenha foi removido por razões de segurança.
    // A validação de senha será feita pelo Spring Security na camada de serviço.
}