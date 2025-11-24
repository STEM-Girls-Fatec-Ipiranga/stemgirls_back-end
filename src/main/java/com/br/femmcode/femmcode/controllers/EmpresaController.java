package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.config.JwtUtils;
import com.br.femmcode.femmcode.dtos.EmpresaDTO;
import com.br.femmcode.femmcode.dtos.JwtResponse;
import com.br.femmcode.femmcode.dtos.LoginRequest;
import com.br.femmcode.femmcode.models.Empresa;
import com.br.femmcode.femmcode.models.StatusEmpresa;
import com.br.femmcode.femmcode.models.Usuario;
import com.br.femmcode.femmcode.repositories.EmpresaRepository;
import com.br.femmcode.femmcode.services.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "http://localhost:5173")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EmpresaRepository empresaRepository;

    @PostMapping("/criar")
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = empresaService.criarEmpresa(empresaDTO);
        return ResponseEntity.ok(empresa);
    }

    @PostMapping("/login")
    public ResponseEntity<Empresa> login(@RequestBody EmpresaDTO dto) {
        Empresa empresa = empresaService.login(dto.email(), dto.senha());
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> encontrarEmpresa(@PathVariable String id) {
        Empresa empresa = empresaService.encontrarEmpresa(id);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @PutMapping("/{email}/aprovar")
    public ResponseEntity<Empresa> aprovar(@PathVariable String email){
        Empresa empresa = empresaService.aprovarEmpresa(email);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @PutMapping("/{email}/reprovar")
    public ResponseEntity<Empresa> reprovar(@PathVariable String email) {
        Empresa empresa = empresaService.reprovarEmpresa(email);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
}
