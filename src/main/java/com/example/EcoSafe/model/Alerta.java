package com.example.EcoSafe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "alerta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Long idAlerta;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", nullable = false)
    @JsonBackReference("evento-alertas")
    @NotNull(message = "Evento é obrigatório")
    private Evento evento;
    
    @NotBlank(message = "Mensagem é obrigatória")
    @Size(max = 500, message = "Mensagem deve ter no máximo 500 caracteres")
    @Column(name = "mensagem", nullable = false, length = 500)
    private String mensagem;
    
    @Size(max = 20, message = "Nível de urgência deve ter no máximo 20 caracteres")
    @Column(name = "nivel_urgencia", length = 20)
    private String nivelUrgencia;
    
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    
    @OneToMany(mappedBy = "alerta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("alerta-usuarios")
    private List<UsuarioAlerta> usuarioAlertas;
    
    @PrePersist
    protected void onCreate() {
        if (dataHora == null) {
            dataHora = LocalDateTime.now();
        }
    }
} 