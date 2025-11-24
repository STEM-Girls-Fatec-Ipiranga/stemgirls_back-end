package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.config.JwtUtils;
import com.br.femmcode.femmcode.dtos.JwtResponse;
import com.br.femmcode.femmcode.dtos.LoginRequest;
import com.br.femmcode.femmcode.dtos.SignUpRequestUsuario;
import com.br.femmcode.femmcode.dtos.UsuarioDTO;
import com.br.femmcode.femmcode.models.Empresa;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.services.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO dto) {
        Usuario usuario = usuarioService.criarUsuario(dto);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody UsuarioDTO dto) {
        Usuario user = usuarioService.login(dto.email(), dto.senha());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
