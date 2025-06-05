package com.example.EcoSafe.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;
    
    @NotBlank(message = "Tipo do evento é obrigatório")
    @Size(max = 50, message = "Tipo do evento deve ter no máximo 50 caracteres")
    @Column(name = "tipo_evento", nullable = false, length = 50)
    private String tipoEvento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_local", nullable = false)
    @JsonBackReference("local-eventos")
    @NotNull(message = "Local é obrigatório")
    private Local local;
    
    @Size(max = 20, message = "Nível de risco deve ter no máximo 20 caracteres")
    @Column(name = "nivel_risco", length = 20)
    private String nivelRisco;
    
    @Size(max = 500, message = "Detalhes devem ter no máximo 500 caracteres")
    @Column(name = "detalhes", length = 500)
    private String detalhes;
    
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("evento-alertas")
    private List<Alerta> alertas;
} 