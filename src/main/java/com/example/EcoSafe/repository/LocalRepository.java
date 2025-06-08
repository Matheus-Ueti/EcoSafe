package com.example.EcoSafe.repository;

import com.example.EcoSafe.model.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
    
    Page<Local> findByCidadeContainingIgnoreCase(String cidade, Pageable pageable);
    Page<Local> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
} 