package com.example.EcoSafe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Entity
@Table(name = "local")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Local {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_local")
    private Long idLocal;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 50, message = "Cidade deve ter no máximo 50 caracteres")
    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;
    
    @NotBlank(message = "Estado é obrigatório")
    @Size(max = 2, message = "Estado deve ter no máximo 2 caracteres")
    @Column(name = "estado", nullable = false, length = 2)
    private String estado;
    
    @Size(max = 100, message = "Coordenadas devem ter no máximo 100 caracteres")
    @Column(name = "coordenadas", length = 100)
    private String coordenadas;
    
    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("local-eventos")
    private List<Evento> eventos;
    
    @OneToMany(mappedBy = "localizacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("local-usuarios")
    private List<Usuario> usuarios;
    
    @OneToMany(mappedBy = "localizacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("local-sensores")
    private List<Sensor> sensores;
} 