package com.example.EcoSafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcoSafe.model.Usuario;
import com.example.EcoSafe.service.UsuarioService;
import com.example.EcoSafe.dto.AuthResponse;
import com.example.EcoSafe.dto.RegisterRequest;
import com.example.EcoSafe.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<AuthResponse> create(@RequestBody @Valid RegisterRequest registerRequest){
        
        var usuario = usuarioService.criarUsuario(registerRequest);
        
        String token = tokenService.generateToken(usuario);
        
        return ResponseEntity.ok(new AuthResponse(token, usuario.getId(), usuario.getNome(), usuario.getEmail()));
    }
}