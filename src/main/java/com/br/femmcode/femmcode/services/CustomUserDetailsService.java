package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.enuns.Role;
import com.br.femmcode.femmcode.models.Empresa;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.repositories.EmpresaRepository;
import com.br.femmcode.femmcode.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Se for empresa → retorna com ROLE_EMPRESA
        Empresa empresa = empresaRepository.findByEmail(email).orElse(null);
        if (empresa != null) {
            List<SimpleGrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority("ROLE_EMPRESA"));

            return new User(
                    empresa.getEmail(),
                    empresa.getSenha(),
                    authorities
            );
        }

        // Se não for empresa, tenta usuário comum
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Adiciona ROLE_USUARIO / ROLE_MODERADOR automaticamente
        List<SimpleGrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name()));

        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                authorities
        );

        //PRIMEIRSA VERSÃO (FUNCIONANTE, TALS)
        // 🔹 Tenta achar primeiro como empresa
        /* Empresa empresa = empresaRepository.findByEmail(email).orElse(null);
        if (empresa != null) {
            return new User(empresa.getEmail(), empresa.getSenha(), new ArrayList<>());
        }

        // 🔹 Se não for empresa, tenta como usuário comum
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());*/

    }
}
