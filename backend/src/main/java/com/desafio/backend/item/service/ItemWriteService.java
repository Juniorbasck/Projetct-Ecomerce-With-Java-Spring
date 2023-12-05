package com.desafio.backend.item.service;

import com.desafio.backend.product.Enums.PromotionEnum;
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
    public Item addItemToCart(long cartId, long productId, long amount) {

        Cart cart = FindCart(cartId);

        Product product = IsProductExist(productId);

        Item existItem = FindProduct(productId, cart);

        if(existItem == null){
            Item item = SetValues(amount, product);

            this.itemRepository.save(item);
            cart.getItem().add(item);
            this.cartRepository.save(cart);

            return item;
        }else{
            this.itemRepository.save(existItem);
            return existItem;
        }
    }

    private static Item SetValues(long amount, Product product) {
        Item item = new Item();
        item.setProduct(product);
        item.setAmount(amount);
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

    private static Item FindProduct(long productId, Cart cart) {
        if (cart != null) {
            for (Item existingItem : cart.getItem()) {
                if (existingItem.getProduct().getId().equals(productId)) {
                   existingItem.setAmount(existingItem.getAmount() +1);
                return existingItem;
                }
            }
        }
    return null;
    }
}
