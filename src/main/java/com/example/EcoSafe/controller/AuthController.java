package com.example.EcoSafe.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcoSafe.model.Usuario;
import com.example.EcoSafe.service.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public record Token(String token, String email, String nome) {}

    public record Credentials(String email, String password) {}

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials) {
        var authentication = new UsernamePasswordAuthenticationToken(
            credentials.email(), 
            credentials.password()
        );
        
        var usuario = (Usuario) authManager.authenticate(authentication).getPrincipal();

        Token tokenObj = tokenService.createToken(usuario);

        Map<String, Object> response = new HashMap<>();
        response.put("token", tokenObj.token());
        response.put("id", usuario.getIdUsuario());
        response.put("nome", usuario.getNome());
        response.put("email", usuario.getEmail());

        return ResponseEntity.ok(response);
    }
}