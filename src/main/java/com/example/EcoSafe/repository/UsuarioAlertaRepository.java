package com.example.EcoSafe.repository;

import com.example.EcoSafe.model.UsuarioAlerta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioAlertaRepository extends JpaRepository<UsuarioAlerta, Long> {
    
    @Query("SELECT ua FROM UsuarioAlerta ua WHERE ua.usuario.idUsuario = :usuarioId")
    Page<UsuarioAlerta> findByUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);
} 