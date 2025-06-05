package com.example.EcoSafe.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    
    private String mensagem;
    private UsuarioResponse usuario;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UsuarioResponse {
        private Long id;
        private String nome;
        private String email;
        private String cpf;
        private String localizacao;
    }
} 