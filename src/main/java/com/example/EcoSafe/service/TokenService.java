package com.example.EcoSafe.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.EcoSafe.model.Usuario;
import com.example.EcoSafe.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TokenService {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public String generateToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        
        return JWT.create()
                .withSubject(usuario.getEmail())
                .withClaim("userId", usuario.getId())
                .withClaim("name", usuario.getNome())
                .withExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS))
                .sign(algorithm);
    }
    
    public UserDetails getUserFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var email = JWT.require(algorithm)
                            .build()
                            .verify(token)
                            .getSubject();
            
            return usuarioRepository.findByEmail(email)
                            .orElseThrow(() -> new JWTVerificationException("Usuário não encontrado"));
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token inválido");
        }
    }
}