package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.dtos.SignUpRequestUsuario;
import com.br.femmcode.femmcode.enuns.Role;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("usuarioService")
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    // --- CADASTRAR NOVO USUÁRIO ---
    public Usuario registrarUsuario(SignUpRequestUsuario dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Erro: E-mail já está em uso!");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNomeCompleto(dto.nomeCompleto());
        novoUsuario.setNomeUsuario(dto.nomeUsuario());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setSenha(passwordEncoder.encode(dto.senha()));
        novoUsuario.setRole(Role.USUARIO);

        return usuarioRepository.save(novoUsuario);
    }

    // --- CARREGAR USUÁRIO PELO EMAIL (para autenticação JWT) ---
    public Usuario loadUserByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));
    }

    // --- IMPLEMENTAÇÃO OBRIGATÓRIA DO UserDetailsService ---
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }

    // --- ESQUECI A SENHA: gera token e envia e-mail ---
    public void processForgotPassword(String email) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("E-mail não encontrado.");
        }

        Usuario usuario = usuarioOpt.get();
        String token = UUID.randomUUID().toString();

        usuario.setPasswordResetToken(token);
        usuarioRepository.save(usuario);

        // envia o e-mail com link de redefinição
        emailService.sendPasswordResetEmail(usuario, token);
    }

    // --- REDEFINIÇÃO DE SENHA ---
    public void processResetPassword(String token, String novaSenha) {
        Usuario usuario = usuarioRepository.findByPasswordResetToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido ou expirado."));

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuario.setPasswordResetToken(null); // limpa o token após redefinir
        usuarioRepository.save(usuario);
    }
}
