package com.br.femmcode.femmcode.config;

import com.br.femmcode.femmcode.enuns.Role;
import com.br.femmcode.femmcode.models.Endereco;
import com.br.femmcode.femmcode.models.Evento;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.repositories.EventoRepository;
import com.br.femmcode.femmcode.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public void run(String... args) {
        criarModerador("talitinhacosta246@gmail.com", "pWsyam1290*", "Talita Vitória", "Talita V.");
        criarModerador("martinskauane084@gmail.com", "@Kauane123", "Kauane Martins", "Kauane M.");
        criarModerador("anaclarafontinelescosta@gmail.com", "Anaclara21#_", "Ana Clara", "Ana Clara");
        criarModerador("leticiaribeiros213@gmail.com", "#Cabecadevento213", "Lethicia Ribeiro", "Lethicia R.");

        Endereco endereco = new Endereco();
        endereco.setCidade("São Paulo");
        endereco.setBairro("Jardim Clímax");
        endereco.setEstado("São Paulo");
        endereco.setNumero("50");
        endereco.setRua("Rua Luiz Inácio");
        endereco.setCep("07233-009");

        Optional<Usuario> organizador = usuarioRepository.findByEmail("talitinhacosta246@gmail.com");

        criarEvento("Reunião meninas digitais", organizador.orElse(null), "Reunião para trocar ideias e conhecimentos sobre o projeto buscando o aprimoramento.", endereco, "26/06/2025", "19:00", "Ao-vivo", "https://res.cloudinary.com/dkgqg84ay/image/upload/v1779230819/meninas-digitais_mnrki5.jpg");
        criarEvento("Palestra tecnologia inclusiva", organizador.orElse(null), "Discussão sobre inclusão de mulheres no mercado de tecnologia.", endereco, "28/06/2025", "15:00", "Presencial", "https://res.cloudinary.com/dkgqg84ay/image/upload/v1779230859/meninas_digitais_congresso_a8ne16.jpg");
        criarEvento("Workshop React para iniciantes", organizador.orElse(null), "Oficina prática para quem deseja aprender os fundamentos do React.", endereco, "05/07/2025", "18:00", "Remoto", "https://res.cloudinary.com/dkgqg84ay/image/upload/v1779230883/womakers1_r25z47.jpg");
    }

    private void criarModerador(String email, String senha, String nome, String nomeUsuario) {
        if (!usuarioRepository.existsByEmail(email)) {
            Usuario m = new Usuario();
            m.setEmail(email);
            m.setSenha(passwordEncoder.encode(senha));
            m.setNome(nome);
            m.setNomeUsuario(nomeUsuario);
            m.setRole(Role.MODERADOR);
            usuarioRepository.save(m);
            System.out.println("✔ Moderadora criada: " + email);
        }
    }

    private void criarEvento(String titulo, Usuario organizador, String descricao, Endereco endereco, String data, String hora, String modalidade, String imagem){
        if(!eventoRepository.existsByTitulo(titulo)){
            Evento e = new Evento();

            e.setTitulo(titulo);
            e.setOrganizador(organizador);
            e.setEndereco(endereco);
            e.setDescricao(descricao);

            e.setData(data);
            e.setHora(hora);
            e.setModalidade(modalidade);

            e.setImagem(imagem);

            eventoRepository.save(e);
            System.out.println("✔ Evento criado: " + titulo);
        }
    }
}
