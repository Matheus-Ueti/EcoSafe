package com.example.EcoSafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    
    private String token;
    private Long userId;
    private String nome;
    private String email;
    
    // Construtor simples usado para login
    public AuthResponse(String token) {
        this.token = token;
    }
}