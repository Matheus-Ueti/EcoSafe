package com.example.EcoSafe.repository;

import com.example.EcoSafe.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    
    @Query("SELECT e FROM Evento e WHERE e.tipoEvento LIKE %:tipo%")
    Page<Evento> findByTipoContaining(@Param("tipo") String tipo, Pageable pageable);
} 