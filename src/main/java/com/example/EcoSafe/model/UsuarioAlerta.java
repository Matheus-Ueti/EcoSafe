package com.example.EcoSafe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_alerta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAlerta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_alerta")
    private Long idUsuarioAlerta;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonBackReference("usuario-alertas")
    @NotNull(message = "Usuário é obrigatório")
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alerta", nullable = false)
    @JsonBackReference("alerta-usuarios")
    @NotNull(message = "Alerta é obrigatório")
    private Alerta alerta;
    
    @Size(max = 20, message = "Status de visualização deve ter no máximo 20 caracteres")
    @Column(name = "status_visualizacao", length = 20)
    private String statusVisualizacao;
    
    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;
    
    @PrePersist
    protected void onCreate() {
        if (dataEnvio == null) {
            dataEnvio = LocalDateTime.now();
        }
        if (statusVisualizacao == null) {
            statusVisualizacao = "NAO_VISUALIZADO";
        }
    }
} 