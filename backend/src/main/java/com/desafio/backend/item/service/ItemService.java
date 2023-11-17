package com.desafio.backend.item.service;

import com.desafio.backend.item.Item;

import java.util.List;

public interface ItemService {

    Item addItemToCart(long cartId, long productId, long amount);
}
