package com.desafio.backend.promotion;

public class TresPorDez extends Promotion{
    @Override
    public Long calculatePrice(Long regularPrice, Long amount) {
        return (amount / 3) * 10 + (amount % 3) * regularPrice;
    }
}
