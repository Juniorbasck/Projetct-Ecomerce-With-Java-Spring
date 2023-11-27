package com.desafio.backend.promotion;

public abstract class Promotion {
    public abstract Long calculatePrice(Long regularPrice, Long amount);
}