package com.desafio.backend.cart.Service;

import lombok.Data;

@Data
public class CartUpdateReques {

    private Long productId;
    private Long amount;
}
