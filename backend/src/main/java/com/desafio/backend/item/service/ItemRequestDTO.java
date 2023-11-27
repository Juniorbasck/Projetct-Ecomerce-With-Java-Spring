package com.desafio.backend.item.service;

import lombok.Data;

@Data
public class ItemRequestDTO {
    private Long productId;
    private Long amount;
}
