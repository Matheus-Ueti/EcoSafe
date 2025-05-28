package com.example.EcoSafe.repository;

import com.example.EcoSafe.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Buscar usuário por email (para autenticação)
    Optional<Usuario> findByEmail(String email);
    
    // Verificar se email já existe
    boolean existsByEmail(String email);
    
    // Verificar se CPF já existe
    boolean existsByCpf(String cpf);
    
    // Buscar usuários por nome
    Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
} 