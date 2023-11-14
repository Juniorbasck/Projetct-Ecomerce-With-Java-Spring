package com.desafio.backend.cart.Controller;

import com.desafio.backend.cart.Cart;
import com.desafio.backend.cart.Service.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/{cartId}")
    public Cart getTheCart(@PathVariable Long cartId){
        return cartRepository.findCartById(cartId);
    }

    @DeleteMapping("/{cartId/{productId}")
    public  Cart deleteProduct(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart != null) {
            cart.getItem().removeIf(item -> item.getProduct().getId().equals(productId));
            return cartRepository.save(cart);
        } else {
            return null;
        }
    }
}
