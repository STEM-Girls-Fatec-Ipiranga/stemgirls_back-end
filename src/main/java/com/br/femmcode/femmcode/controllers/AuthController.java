package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.config.JwtUtils;
import com.br.femmcode.femmcode.dtos.JwtResponse;
import com.br.femmcode.femmcode.dtos.LoginRequest;
import com.br.femmcode.femmcode.dtos.SignUpRequestUsuario;
import com.br.femmcode.femmcode.models.Empresa;
import com.br.femmcode.femmcode.models.StatusEmpresa;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.services.EmpresaService;
import com.br.femmcode.femmcode.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    // --- LOGIN USUÁRIO ---
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            Usuario user = usuarioService.loadUserByEmail(loginRequest.email());
            return ResponseEntity.ok(new JwtResponse(jwt, user));

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Erro: E-mail ou senha inválidos para usuário.");
        }
    }

    // --- LOGIN EMPRESA ---
   @PostMapping("/login-empresa")
public ResponseEntity<?> loginEmpresa(@RequestBody LoginRequest loginRequest) {
    try {
        Empresa empresa = empresaService.findByEmail(loginRequest.email());

        // Verifica senha
        if (!empresaService.passwordMatches(loginRequest.senha(), empresa.getSenha())) {
            return ResponseEntity.status(401).body("Erro: E-mail ou senha inválidos para empresa.");
        }

        // Verifica status
        if (empresa.getStatus() != StatusEmpresa.APROVADO) {
            return ResponseEntity.status(403).body("Erro: Sua conta ainda está em análise ou foi reprovada.");
        }

        // Gera JWT
        String jwt = jwtUtils.generateJwtTokenFromEmail(empresa.getEmail());

        // Retorna resposta
        return ResponseEntity.ok(new JwtResponse(jwt, empresa));

    } catch (RuntimeException e) {
        return ResponseEntity.status(401).body("Erro: " + e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
    }
}


    // --- ESQUECI A SENHA (USUÁRIO) ---
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody LoginRequest emailRequest) {
        try {
            usuarioService.processForgotPassword(emailRequest.email());
            return ResponseEntity.ok("Se o e-mail existir em nossa base, um link de redefinição foi enviado.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao processar a solicitação: " + e.getMessage());
        }
    }

    // --- REDEFINIR SENHA (USUÁRIO) ---
    @PostMapping("/reset-password/{token}")
    public ResponseEntity<String> resetPassword(@PathVariable String token, @RequestBody ResetPasswordRequest passwordRequest) {
        try {
            usuarioService.processResetPassword(token, passwordRequest.newPassword());
            return ResponseEntity.ok("Senha redefinida com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DTO para a requisição de redefinição de senha
    private record ResetPasswordRequest(String newPassword) {}
}
