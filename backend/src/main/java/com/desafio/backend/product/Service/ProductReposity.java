package com.desafio.backend.product.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.backend.product.Product;

public interface ProductReposity extends JpaRepository<Product, Long>{
 
}