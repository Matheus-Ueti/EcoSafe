package com.example.EcoSafe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "leitura_sensor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraSensor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_leitura")
    private Long idLeitura;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sensor", nullable = false)
    @JsonBackReference("sensor-leituras")
    @NotNull(message = "Sensor é obrigatório")
    private Sensor sensor;
    
    @Column(name = "valor_lido", precision = 10, scale = 2)
    private BigDecimal valorLido;
    
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    
    @PrePersist
    protected void onCreate() {
        if (dataHora == null) {
            dataHora = LocalDateTime.now();
        }
    }
} 