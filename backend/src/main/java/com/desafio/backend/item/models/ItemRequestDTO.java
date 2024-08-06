package com.desafio.backend.item.models;

import com.desafio.backend.product.Enums.PromotionEnum;
import lombok.Data;

@Data
public class ItemRequestDTO {
    private Long productId;
    private Long amount;
}
