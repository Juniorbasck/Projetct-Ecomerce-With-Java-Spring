package com.desafio.backend.cart.Service;


import com.desafio.backend.cart.Cart;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartById(Long id);
}
