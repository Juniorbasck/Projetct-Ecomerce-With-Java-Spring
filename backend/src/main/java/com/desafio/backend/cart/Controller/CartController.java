package com.desafio.backend.cart.Controller;

import com.desafio.backend.cart.Cart;
import com.desafio.backend.cart.Service.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}




