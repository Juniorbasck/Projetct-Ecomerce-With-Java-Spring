package com.desafio.backend.item.service;

import com.desafio.backend.item.Item;
import com.desafio.backend.cart.Cart;
import com.desafio.backend.cart.Service.CartRepository;
import com.desafio.backend.product.Product;
import com.desafio.backend.product.Service.ProductReposity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemWriteService implements ItemService {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductReposity productRepository;

    @Override
    public Item addItemToCart(long cartId, long productId, long amount) {

        Optional<Cart> optionalCart = this.cartRepository.findById(cartId);
        if (((Optional<?>) optionalCart).isPresent()) {
            Cart cart = optionalCart.get();
            for (Item existingItem : cart.getItem()) {
                if (existingItem.getProduct().getId().equals(productId)) {
                    throw new RuntimeException("Produto já existe no carrinho");
                }
            }
        }
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + productId));

        Cart cart = this.cartRepository.findById(cartId).orElse(new Cart());

        Item item = new Item();
        item.setProduct(product);
        item.setAmount(amount);

        this.itemRepository.save(item);

        cart.getItem().add(item);
        this.cartRepository.save(cart);

        return item;
    }
}
