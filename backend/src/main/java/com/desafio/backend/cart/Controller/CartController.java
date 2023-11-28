package com.desafio.backend.cart.Controller;

import com.desafio.backend.cart.Cart;
import com.desafio.backend.cart.Models.CartWithTotalPriceDTO;
import com.desafio.backend.cart.Service.CartRepository;
import com.desafio.backend.cart.Models.CartUpdateRequesDTO;
import com.desafio.backend.cart.Service.CartWriteService;
import com.desafio.backend.item.Item;
import com.desafio.backend.item.repository.ItemRepository;
import com.desafio.backend.product.Service.ProductReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private CartWriteService cartWriteService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{cartId}")
    public CartWithTotalPriceDTO getTheCart(@PathVariable Long cartId) {
        Cart cart = cartRepository.findCartById(cartId);
        Long totalPrice = cartWriteService.CalculateTotalPrice(cart);
        return new CartWithTotalPriceDTO(cart, totalPrice);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{cartId}/{itemId}")
    public Cart deleteItem(@PathVariable Long cartId, @PathVariable Long itemId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart != null) {
            cart.getItem().removeIf(item -> item.getId().equals(itemId));
            return cartRepository.save(cart);
        } else {
            return null;
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{cartId}")
    public Cart updateCartItems(@PathVariable Long cartId, @RequestBody List<CartUpdateRequesDTO> requests) {

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Carrinho não encontrado com o ID: " + cartId));

        for (CartUpdateRequesDTO request : requests) {
            Long productId = request.getProductId();
            Long amount = request.getAmount();

            for (Item item : cart.getItem()) {
                if (item.getProduct().getId().equals(productId)) {
                    item.setAmount(amount);
                    break;
                }
            }
        }
        return cartRepository.save(cart);
    }
}

