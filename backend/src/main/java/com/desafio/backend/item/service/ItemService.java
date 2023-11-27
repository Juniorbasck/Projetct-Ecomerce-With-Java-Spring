package com.desafio.backend.item.service;

import com.desafio.backend.item.Enums.PromotionEnum;
import com.desafio.backend.item.Item;

public interface ItemService {
    Item addItemToCart(long cartId, long productId, long amount, PromotionEnum promotion);
}
