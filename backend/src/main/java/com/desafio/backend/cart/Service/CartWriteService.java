package com.desafio.backend.cart.Service;

import com.desafio.backend.cart.Cart;
import com.desafio.backend.item.Item;
import com.desafio.backend.product.Product;
import com.desafio.backend.promotion.Promotion;
import com.desafio.backend.promotion.Services.PromotionWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartWriteService {

    @Autowired
    private PromotionWriteService promotionWriteService;
    @Autowired
    private Promotion promotion;

//    public CartWriteService(Promotion promotion){
//        this.promotion = promotion;
//    }

    public Long CalculateTotalPrice(Cart cart) {

        Long totalPrice = 0L;

        for (Item item : cart.getItem()) {
            Product product = item.getProduct();
            Long regularPrice = product.getPrice();
            Long amount = item.getAmount();

            if (product.getPromotion() != null) {
                totalPrice += promotion.calculatePrice(regularPrice, amount);
            }

            totalPrice = promotionWriteService.CalculateWithPromtion(product, regularPrice, amount);
        }

        return totalPrice;
    }
}
