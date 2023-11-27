package com.desafio.backend.promotion;

public class LeveDoisPagueUm extends Promotion {
    @Override
    public Long calculatePrice(Long regularPrice, Long amount) {
        return (amount / 2) * regularPrice + (amount % 2) * regularPrice;
    }
}
