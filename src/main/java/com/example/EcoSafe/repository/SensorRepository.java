package com.example.EcoSafe.repository;

import com.example.EcoSafe.model.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    
    Page<Sensor> findByTipoContainingIgnoreCase(String tipo, Pageable pageable);
    Page<Sensor> findByStatus(String status, Pageable pageable);
} 