package com.quantumtrader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantumtrader.entity.Stock;
/*
 * Data access layer. We don't need to implement many methods here since they are provided for in
 * JpaRepository and its parent classes.
 */
public interface StockRepository extends JpaRepository<Stock, String> {
    
}
