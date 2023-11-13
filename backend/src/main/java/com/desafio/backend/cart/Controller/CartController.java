package com.desafio.backend.cart.Controller;

import com.desafio.backend.cart.Cart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    @GetMapping
    public List<Cart> getTheCard(){

        return null;
    }
}




