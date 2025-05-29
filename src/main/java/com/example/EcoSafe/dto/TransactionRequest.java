package com.example.EcoSafe.dto;

import com.example.EcoSafe.model.Transaction;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionRequest {
    
    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 3, max = 200, message = "Descrição deve ter entre 3 e 200 caracteres")
    private String description;
    
    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal amount;
    
    @NotNull(message = "Tipo é obrigatório")
    private Transaction.TransactionType type;
    
    @NotNull(message = "Data da transação é obrigatória")
    private LocalDateTime transactionDate;
    
    @NotNull(message = "Categoria é obrigatória")
    private Long categoryId;
    
    @Size(max = 500, message = "Observações devem ter no máximo 500 caracteres")
    private String observations;
} 