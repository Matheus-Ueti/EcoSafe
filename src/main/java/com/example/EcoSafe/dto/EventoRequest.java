package com.example.EcoSafe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EventoRequest {
    
    @NotBlank(message = "Tipo do evento é obrigatório")
    @Size(max = 50, message = "Tipo do evento deve ter no máximo 50 caracteres")
    private String tipoEvento;
    
    @NotNull(message = "Local é obrigatório")
    private Long localId;
    
    @Size(max = 20, message = "Nível de risco deve ter no máximo 20 caracteres")
    private String nivelRisco;
    
    @Size(max = 500, message = "Detalhes devem ter no máximo 500 caracteres")
    private String detalhes;
}