package com.desafio.backend.item.service;

import lombok.Data;

@Data
public class ItemRequest {
    private Long cartId;
    private Long productId;
    private Long amount;
}