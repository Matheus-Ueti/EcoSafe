package com.example.EcoSafe.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcoSafe.model.Usuario;
import com.example.EcoSafe.service.TokenService;
import com.example.EcoSafe.dto.AuthResponse;
import com.example.EcoSafe.dto.LoginRequest;

import jakarta.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest credentials) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
            credentials.getEmail(), credentials.getSenha());
        var authentication = authManager.authenticate(authenticationToken);
        var usuario = (Usuario) authentication.getPrincipal();
        
        String token = tokenService.generateToken(usuario);
        return ResponseEntity.ok(new AuthResponse(token, usuario.getId(), usuario.getNome(), usuario.getEmail()));
    }
}