package com.desafio.backend.item.service;

import com.desafio.backend.item.Item;
import com.desafio.backend.cart.Cart;
import com.desafio.backend.cart.Service.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemImplementation implements ItemService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Item addItemToCart(Long cartId, Item item) {

        Cart cart = cartRepository.findById(cartId).orElse(new Cart());

        boolean itemExists = false;
        for (Item cartItem : cart.getItems()) {
            if (cartItem.getProduct().getId().equals(item.getProduct().getId())) {

                cartItem.setAmount(cartItem.getAmount() + item.getAmount());
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            cart.getItems().add(item);
        }

        cartRepository.save(cart);

        return item;
    }
}


