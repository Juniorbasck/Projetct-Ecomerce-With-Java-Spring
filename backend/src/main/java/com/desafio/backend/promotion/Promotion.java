package com.desafio.backend.promotion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Promotion {
    public Long calculatePrice(Long regularPrice, Long amount) {
        return regularPrice * amount;
    }
}