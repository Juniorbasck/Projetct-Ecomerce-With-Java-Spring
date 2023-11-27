package com.desafio.backend.product.Service;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.backend.product.Product;


@Transactional
public interface ProductReposity extends JpaRepository<Product, Long>{
    Product getProductById(Long id);
}