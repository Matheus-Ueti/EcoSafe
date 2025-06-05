package com.example.EcoSafe.repository;

import com.example.EcoSafe.model.Evento;
import com.example.EcoSafe.model.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    
    // Busca por tipo de evento (case insensitive)
    Page<Evento> findByTipoEventoContainingIgnoreCase(String tipoEvento, Pageable pageable);
    
    // Busca por local
    Page<Evento> findByLocal(Local local, Pageable pageable);
}