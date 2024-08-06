package com.desafio.backend.cart.Models;

import lombok.Data;

@Data
public class CartUpdateRequesDTO {
    private Long productId;
    private Long amount;
    private Long originalAmount;
}
