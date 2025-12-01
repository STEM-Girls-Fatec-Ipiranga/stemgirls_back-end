package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.dtos.UsuarioDTO;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.services.CloudinaryService;
import com.br.femmcode.femmcode.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO dto) {
        Usuario usuario = usuarioService.criarUsuario(dto);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody UsuarioDTO dto){
        Usuario usuario = usuarioService.atualizarUsuario(dto);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody UsuarioDTO dto) {
        Usuario user = usuarioService.login(dto.email(), dto.senha());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            String result = cloudinaryService.uploadFile(file);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro no upload: " + e.getMessage());
        }
    }
}
