package com.desafio.backend.item.service;

import com.desafio.backend.item.Enums.PromotionEnum;
import com.desafio.backend.item.Item;
import com.desafio.backend.cart.Cart;
import com.desafio.backend.cart.Service.CartRepository;
import com.desafio.backend.item.repository.ItemRepository;
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
    public Item addItemToCart(long cartId, long productId, long amount, PromotionEnum promotion) {

        validations(cartId, productId);

        Product product = IsProductExist(productId);

        Cart cart = FindCart(cartId);

        Item item = SetValues(amount, promotion, product);

        this.itemRepository.save(item);
        cart.getItem().add(item);
        this.cartRepository.save(cart);

        return item;
    }

    private static Item SetValues(long amount, PromotionEnum promotion, Product product) {
        Item item = new Item();
        item.setProduct(product);
        item.setAmount(amount);
        item.setPromotion(promotion);
        return item;
    }

    private Cart FindCart(long cartId) {
        Cart cart = this.cartRepository.findById(cartId).orElse(new Cart());
        return cart;
    }

    private Product IsProductExist(long productId) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + productId));
        return product;
    }

    private void validations(long cartId, long productId) {
        Optional<Cart> optionalCart = this.cartRepository.findById(cartId);
        FindProduct(productId, optionalCart);
    }

    private static void FindProduct(long productId, Optional<Cart> optionalCart) {
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            for (Item existingItem : cart.getItem()) {
                if (existingItem.getProduct().getId().equals(productId)) {
                    throw new RuntimeException("Produto já existe no carrinho");
                }
            }
        }
    }
}
