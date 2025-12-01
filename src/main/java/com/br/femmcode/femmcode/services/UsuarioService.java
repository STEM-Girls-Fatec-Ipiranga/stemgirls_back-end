package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.dtos.UsuarioDTO;
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

    public Usuario criarUsuario(UsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("E-mail já está em uso!");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNomeCompleto(dto.nomeCompleto());
        novoUsuario.setNomeUsuario(dto.nomeUsuario());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setSenha(passwordEncoder.encode(dto.senha()));
        novoUsuario.setRole(Role.USUARIO);

        return usuarioRepository.save(novoUsuario);
    }

    public Usuario atualizarUsuario(UsuarioDTO dto){
        if (!usuarioRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Usuário não existe!");
        }

        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.nomeCompleto());
        usuario.setNomeUsuario(dto.nomeUsuario());
        usuario.setSobre(dto.sobre());
        usuario.setLinkImagemPerfil(dto.linkImagemPerfil());

        return usuarioRepository.save(usuario);
    }

    public Usuario login(String email, String senha){
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if(!passwordEncoder.matches(senha, usuario.getSenha())){
            throw new RuntimeException("Erro ao realizar login!");
        }

        return usuario;
    }

    public Usuario encontrarUsuario(String id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    public Usuario loadUserByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }

    public void processForgotPassword(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        String token = UUID.randomUUID().toString();

        usuario.setPasswordResetToken(token);
        usuarioRepository.save(usuario);

        emailService.sendPasswordResetEmail(usuario, token);
    }

    public void processResetPassword(String token, String novaSenha) {
        Usuario usuario = usuarioRepository.findByPasswordResetToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido ou expirado."));

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuario.setPasswordResetToken(null);
        usuarioRepository.save(usuario);
    }
}
