package com.example.EcoSafe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sensor")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sensor")
    private Long idSensor;
    
    @NotBlank(message = "Tipo é obrigatório")
    @Size(max = 50, message = "Tipo deve ter no máximo 50 caracteres")
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localizacao", referencedColumnName = "id_local")
    @JsonBackReference("local-sensores")
    private Local localizacao;
    
    @Size(max = 20, message = "Unidade de medida deve ter no máximo 20 caracteres")
    @Column(name = "unidade_medida", length = 20)
    private String unidadeMedida;
    
    @Size(max = 20, message = "Status deve ter no máximo 20 caracteres")
    @Column(name = "status", length = 20)
    private String status;
    
} 