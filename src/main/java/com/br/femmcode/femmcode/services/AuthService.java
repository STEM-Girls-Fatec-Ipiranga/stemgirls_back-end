package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.dtos.SignUpRequest;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public Usuario registerUser(SignUpRequest signUpRequest) {
        if (usuarioRepository.existsByEmail(signUpRequest.email())) {
            throw new RuntimeException("Erro: E-mail já está em uso!");
        }
        Usuario newUser = new Usuario();
        newUser.setNomeCompleto(signUpRequest.nomeCompleto());
        newUser.setNomeUsuario(signUpRequest.nomeUsuario());
        newUser.setEmail(signUpRequest.email());
        newUser.setSenha(passwordEncoder.encode(signUpRequest.senha()));
        return usuarioRepository.save(newUser);
    }

    public void processForgotPassword(String email) {
        // Busca o usuário pelo e-mail. Se existir, executa a lógica.
        usuarioRepository.findByEmail(email).ifPresent(user -> {
            String token = UUID.randomUUID().toString();
            user.setPasswordResetToken(token);
            user.setPasswordResetTokenExpiryDate(LocalDateTime.now().plusHours(1)); // Token válido por 1 hora
            usuarioRepository.save(user);

            // Chama o serviço de e-mail para enviar o link com o token
            emailService.sendPasswordResetEmail(user.getEmail(), token);
        });
        // Nota: Propositalmente não retornamos erro se o e-mail não existir,
        // para não expor quais e-mails estão cadastrados no sistema (prática de segurança).
    }

    public void processResetPassword(String token, String newPassword) {
        // Busca o usuário pelo token
        Usuario user = usuarioRepository.findByPasswordResetToken(token)
                .orElseThrow(() -> new RuntimeException("Token de redefinição inválido ou expirado."));

        // Verifica se o token expirou
        if (user.getPasswordResetTokenExpiryDate().isBefore(LocalDateTime.now())) {
            // Limpa o token para segurança e lança o erro
            user.setPasswordResetToken(null);
            user.setPasswordResetTokenExpiryDate(null);
            usuarioRepository.save(user);
            throw new RuntimeException("Token de redefinição expirado.");
        }

        // Se o token for válido, atualiza a senha e limpa os campos do token
        user.setSenha(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiryDate(null);
        usuarioRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        return new User(user.getEmail(), user.getSenha(), new ArrayList<>());
    }
}