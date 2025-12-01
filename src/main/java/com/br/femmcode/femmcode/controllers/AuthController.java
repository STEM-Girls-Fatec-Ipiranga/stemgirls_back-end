package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.dtos.UsuarioDTO;
import com.br.femmcode.femmcode.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody UsuarioDTO dto) {
        try {
            usuarioService.processForgotPassword(dto.email());
            return ResponseEntity.ok("Se o e-mail existir em nossa base, um link de redefinição foi enviado.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao processar a solicitação: " + e.getMessage());
        }
    }

    @PostMapping("/reset-password/{token}")
    public ResponseEntity<String> resetPassword(@PathVariable String token, @RequestBody UsuarioDTO dto) {
        try {
            usuarioService.processResetPassword(token, dto.senha());
            return ResponseEntity.ok("Senha redefinida com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
