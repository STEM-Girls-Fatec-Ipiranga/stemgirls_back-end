package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.dtos.EmpresaDTO;
import com.br.femmcode.femmcode.enuns.Role;
import com.br.femmcode.femmcode.models.Empresa;
import com.br.femmcode.femmcode.models.Notificacao;
import com.br.femmcode.femmcode.services.NotificacaoService;
import com.br.femmcode.femmcode.models.StatusEmpresa;
import com.br.femmcode.femmcode.repositories.EmpresaRepository;
import com.br.femmcode.femmcode.repositories.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("empresaService")
public class EmpresaService implements UserDetailsService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public Empresa criarEmpresa(EmpresaDTO dto) {
        if (empresaRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Erro: E-mail já está em uso!");
        }
        if (empresaRepository.existsByCnpj(dto.cnpj())) {
             throw new RuntimeException("Erro: CNPJ já está cadastrado!");
        }

        Empresa newEmpresa = new Empresa();
        newEmpresa.setNomeEmpresa(dto.nomeEmpresa());
        newEmpresa.setCnpj(dto.cnpj());
        newEmpresa.setEmail(dto.email());
        newEmpresa.setSenha(passwordEncoder.encode(dto.senha()));
        newEmpresa.setTelefone(dto.telefone());
        newEmpresa.setStatus(StatusEmpresa.PENDENTE);
        newEmpresa.setRole(Role.EMPRESA);

        Empresa empresa = empresaRepository.save(newEmpresa);
        emailService.sendEmpresaApprovalEmail(empresa);
        notificacaoService.criarNotificacao(empresa);

        return empresa;
    }

    public Empresa login(String email, String senha) {
        Empresa empresa = empresaRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada!"));

        if(!passwordEncoder.matches(senha, empresa.getSenha())){
            throw new RuntimeException("Erro ao realizar login!");
        }

        if(empresa.getStatus()==StatusEmpresa.PENDENTE){
            throw new RuntimeException("Empresa pendente. Ainda estamos analisando seus dados.");
        }

        if(empresa.getStatus()==StatusEmpresa.REPROVADO){
            throw new RuntimeException("Infelizmente a sua empresa foi reprovada. Entre em contato conosco para mais detalhes.");
        }

        return empresa;
    }

    public Empresa aprovarEmpresa(String email) {
        Empresa empresa = empresaRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada!"));

        if(empresa.getStatus()!=StatusEmpresa.PENDENTE)
            throw new RuntimeException("Empresa não está mais pendente!");

        empresa.setStatus(StatusEmpresa.APROVADO);

        notificacaoService.deletarNotificacao(empresa.getId());
        emailService.sendEmpresaAprovadaEmail(empresa);

        return empresaRepository.save(empresa);
    }

    public Empresa reprovarEmpresa(String email) {
        Empresa empresa = empresaRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada!"));

        if(empresa.getStatus()!=StatusEmpresa.PENDENTE)
            throw new RuntimeException("Empresa não está mais pendente!");

        empresa.setStatus(StatusEmpresa.REPROVADO);

        notificacaoService.deletarNotificacao(empresa.getId());
        emailService.sendEmpresaReprovadaEmail(empresa);

        return empresaRepository.save(empresa);
    }

    public Empresa encontrarEmpresa(String id){
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada!"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Empresa empresa = empresaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        if (empresa.getRole() == Role.EMPRESA && empresa.getStatus() != StatusEmpresa.APROVADO) {
            throw new RuntimeException("Sua conta de empresa ainda está em análise.");
        }

        return new User(empresa.getEmail(), empresa.getSenha(), new ArrayList<>());
    }
}
