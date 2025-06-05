package com.example.EcoSafe.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.EcoSafe.controller.AuthController.Token;
import com.example.EcoSafe.model.Usuario;

@Service
public class TokenService {

    Instant expiresAt = LocalDateTime.now()
            .plusMinutes(120)
            .toInstant(ZoneOffset.ofHours(-3));
    Algorithm algorithm = Algorithm.HMAC256("secret");

    public Token createToken(Usuario usuario) {
        var jwt = JWT.create()
                .withSubject(usuario.getIdUsuario().toString())
                .withClaim("email", usuario.getEmail())
                .withClaim("nome", usuario.getNome())
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new Token(jwt, usuario.getEmail(), usuario.getNome());
    }

    public Usuario getUsuarioFromToken(String token) {
        var verifiedToken = JWT.require(algorithm)
                .build()
                .verify(token);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(Long.valueOf(verifiedToken.getSubject()));
        usuario.setEmail(verifiedToken.getClaim("email").asString());
        usuario.setNome(verifiedToken.getClaim("nome").asString());
        return usuario;
    }
}