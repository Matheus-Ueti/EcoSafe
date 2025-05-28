package com.example.EcoSafe.repository;

import com.example.EcoSafe.model.Alerta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    
    @Query("SELECT a FROM Alerta a WHERE a.nivelUrgencia = :nivel")
    Page<Alerta> findByNivelUrgencia(@Param("nivel") String nivel, Pageable pageable);
} 