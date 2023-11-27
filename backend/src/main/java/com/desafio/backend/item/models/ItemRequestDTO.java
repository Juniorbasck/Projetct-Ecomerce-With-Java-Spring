package com.desafio.backend.item.models;

import com.desafio.backend.item.Enums.PromotionEnum;
import lombok.Data;

@Data
public class ItemRequestDTO {
    private Long productId;
    private Long amount;
    private PromotionEnum promotion;
}
