package com.example.EcoSafe.dto;

import com.example.EcoSafe.model.Transaction;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private Long id;
    private String description;
    private BigDecimal amount;
    private Transaction.TransactionType type;
    private LocalDateTime transactionDate;
    private String categoryName;
    private Long categoryId;
    private String userName;
    private Long userId;
    private String observations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 