package com.desafio.backend.cart.Controller;

import com.desafio.backend.cart.Cart;
import com.desafio.backend.cart.Service.CartRepository;
import com.desafio.backend.cart.Service.CartUpdateReques;
import com.desafio.backend.item.Item;
import com.desafio.backend.item.service.ItemRepository;
import com.desafio.backend.product.Product;
import com.desafio.backend.product.Service.ProductReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductReposity productReposity;

    @GetMapping("/{cartId}")
    public Cart getTheCart(@PathVariable Long cartId) {
        return cartRepository.findCartById(cartId);

    }

    @DeleteMapping("/{cartId}/{productId}")
    public Cart deleteProduct(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart != null) {
            cart.getItem().removeIf(item -> item.getProduct().getId().equals(productId));
            return cartRepository.save(cart);
        } else {
            return null;
        }
    }

    @PutMapping("/{cartId}")
    public Cart updateCartItem(@PathVariable Long cartId, @RequestBody CartUpdateReques request){

        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart != null) {
            for (Item item : cart.getItem()) {
                if (item.getProduct().getId().equals(request.getProductId())) {

                    Product product = productReposity.findById(request.getProductId())
                            .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + request.getProductId()));

                    item.setProduct(product);
                    item.setAmount(request.getAmount());
                    break;
                }
            }
            return cartRepository.save(cart);
        } else {
            return null;
        }
    }
}

