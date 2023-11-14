package com.desafio.backend.item.service;

import com.desafio.backend.item.Item;
import com.desafio.backend.cart.Cart;
import com.desafio.backend.cart.Service.CartRepository;
import com.desafio.backend.product.Product;
import com.desafio.backend.product.Service.ProductReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemImplementation implements ItemService {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductReposity productRepository;

    @Override
    public Item addItemToCart(Long cartId, long productId, long amount) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + productId));

        Cart cart = cartId != null ? cartRepository.findById(cartId).orElse(new Cart()) : new Cart();

        Item item = new Item();
        item.setProduct(product);
        item.setAmount(amount);

        itemRepository.save(item);

        cart.getItem().add(item);
        cartRepository.save(cart);

        return item;
    }
}
