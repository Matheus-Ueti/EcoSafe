package br.com.fiap.money_control_api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.money_control_api.controller.AuthController.Credentials;
import br.com.fiap.money_control_api.controller.AuthController.Token;
import br.com.fiap.money_control_api.model.User;
import br.com.fiap.money_control_api.model.UserInfo;

@Service
public class TokenService {

    Instant expiresAt = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.ofHours(-3));
    Algorithm algorithm = Algorithm.HMAC256("secret");

    public Token createToken(User user){
        var jwt = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().toString())
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new Token(jwt, user.getEmail());
    }

    public User getUserFromToken(String token) {
        var verifiedToken = JWT.require(algorithm).build().verify(token);

        Long id = Long.valueOf(verifiedToken.getSubject());
        String email = verifiedToken.getClaim("email").asString());
        String role = verifiedToken.getClaim("role").asString());

        return User.builder()
                .id(id)
                .email(email)
                .role(UserRole.valueOf(role))
                .build();
    }

}