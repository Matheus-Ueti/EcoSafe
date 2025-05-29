package com.example.EcoSafe.repository;

import com.example.EcoSafe.model.Transaction;
import com.example.EcoSafe.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    Page<Transaction> findByUserOrderByTransactionDateDesc(User user, Pageable pageable);
    
    List<Transaction> findByUserAndTransactionDateBetween(
            User user, 
            LocalDateTime startDate, 
            LocalDateTime endDate
    );
    
    @Query("SELECT t FROM Transaction t WHERE t.user = :user AND " +
           "(:startDate IS NULL OR t.transactionDate >= :startDate) AND " +
           "(:endDate IS NULL OR t.transactionDate <= :endDate) " +
           "ORDER BY t.transactionDate DESC")
    Page<Transaction> findByUserAndDateRange(
            @Param("user") User user,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
} 