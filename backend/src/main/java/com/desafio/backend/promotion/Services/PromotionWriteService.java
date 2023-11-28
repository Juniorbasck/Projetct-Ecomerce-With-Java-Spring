package com.desafio.backend.promotion.Services;

import com.desafio.backend.product.Enums.PromotionEnum;
import com.desafio.backend.product.Product;
import com.desafio.backend.promotion.LeveDoisPagueUm;
import com.desafio.backend.promotion.TresPorDez;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionWriteService {

    @Autowired
    private LeveDoisPagueUm leveDoisPagueUm;

    @Autowired
    private TresPorDez tresPorDez;

    public long CalculateWithPromtion(Product product, Long regularPrice, Long amount){

        Long total = 0L;

        if (product.getPromotion() == PromotionEnum.DoisPorUm){
            total = leveDoisPagueUm.calculatePrice(regularPrice, amount);
        }

        if (product.getPromotion() == PromotionEnum.TresPorDez){
            total = tresPorDez.calculatePrice(regularPrice, amount);
        }

        return total;
    }
}
