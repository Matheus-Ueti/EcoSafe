package com.example.EcoSafe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorRequest {
    
    @NotBlank(message = "Tipo é obrigatório")
    @Size(max = 50, message = "Tipo deve ter no máximo 50 caracteres")
    private String tipo;
    
    @NotNull(message = "ID da localização é obrigatório")
    private Long localizacaoId;
    
    @Size(max = 20, message = "Unidade de medida deve ter no máximo 20 caracteres")
    private String unidadeMedida;
    
    @Size(max = 20, message = "Status deve ter no máximo 20 caracteres")
    private String status;
}
