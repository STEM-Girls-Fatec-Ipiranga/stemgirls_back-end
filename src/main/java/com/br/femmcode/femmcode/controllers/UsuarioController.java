package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.config.JwtUtils;
import com.br.femmcode.femmcode.dtos.JwtResponse;
import com.br.femmcode.femmcode.dtos.LoginRequest;
import com.br.femmcode.femmcode.dtos.SignUpRequestUsuario;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    // --- CADASTRAR NOVO USUÁRIO ---
    @PostMapping("/criar")
    public ResponseEntity<?> registrarUsuario(@RequestBody SignUpRequestUsuario dto) {
        try {
            Usuario novoUsuario = usuarioService.registrarUsuario(dto);
            return ResponseEntity.ok("Usuário " + novoUsuario.getNomeCompleto() + " registrado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    // --- LOGIN (opcional — caso queira manter login separado do AuthController) ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            Usuario user = usuarioService.loadUserByEmail(loginRequest.email());
            return ResponseEntity.ok(new JwtResponse(jwt, user));

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Erro: E-mail ou senha inválidos.");
        }
    }
}
