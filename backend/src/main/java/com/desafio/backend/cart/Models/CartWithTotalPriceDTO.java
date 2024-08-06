package com.desafio.backend.cart.Models;

import com.desafio.backend.cart.Cart;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
public class CartWithTotalPriceDTO {

    private Cart cart;
    private Long totalprice;

    public CartWithTotalPriceDTO(Cart cart, Long totalprice){
        this.cart = cart;
        this.totalprice = totalprice;
    }
}
