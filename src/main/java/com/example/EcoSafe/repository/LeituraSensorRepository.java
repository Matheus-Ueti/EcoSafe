package com.example.EcoSafe.repository;

import com.example.EcoSafe.model.LeituraSensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, Long> {
    
    @Query("SELECT l FROM LeituraSensor l WHERE l.sensor.idSensor = :sensorId")
    Page<LeituraSensor> findBySensorId(@Param("sensorId") Long sensorId, Pageable pageable);
} 