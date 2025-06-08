package com.example.EcoSafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcoSafe.dto.AuthRequest;
import com.example.EcoSafe.dto.AuthResponse;
import com.example.EcoSafe.model.Usuario;
import com.example.EcoSafe.service.TokenService;

@RestController
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        // Criar o token de autenticação
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());
        
        // Autenticar
        Authentication auth = authenticationManager.authenticate(authToken);
        
        // Pegar o usuário logado
        Usuario usuario = (Usuario) auth.getPrincipal();
        
        // Gerar token JWT
        String token = tokenService.generateToken(usuario);
        
        // Retornar token com informações adicionais do usuário
        return ResponseEntity.ok(new AuthResponse(token, usuario.getId(), usuario.getNome(), usuario.getEmail()));
    }
}