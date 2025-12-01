package com.br.femmcode.femmcode.config;

import com.br.femmcode.femmcode.enuns.Role;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        criarModerador("talitinhacosta246@gmail.com", "pWsyam1290*", "Talita Vitória", "Talita V.");
        criarModerador("martinskauane084@gmail.com", "@Kauane123", "Kauane Martins", "Kauane M.");
        criarModerador("anaclarafontinelescosta@gmail.com", "Anaclara21#_", "Ana Clara", "Ana Clara");
        criarModerador("leticiaribeiros213@gmail.com", "#Cabecadevento213", "Lethicia Ribeiro", "Lethicia R.");
    }

    private void criarModerador(String email, String senha, String nomeCompleto, String nomeUsuario) {
        if (!usuarioRepository.existsByEmail(email)) {
            Usuario m = new Usuario();
            m.setEmail(email);
            m.setSenha(passwordEncoder.encode(senha));
            m.setNomeCompleto(nomeCompleto);
            m.setNomeUsuario(nomeUsuario);
            m.setRole(Role.MODERADOR);
            usuarioRepository.save(m);
            System.out.println("✔ Moderadora criada: " + email);
        }
    }
}
