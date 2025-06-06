package br.com.fiap.money_control_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.money_control_api.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByEmail(String username);

} 