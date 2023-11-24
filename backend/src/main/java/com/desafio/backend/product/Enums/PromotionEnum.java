package com.desafio.backend.product.Enums;

public enum PromotionEnum {
    DoisPorUm(1),
    TresPorDez(2);

    private int value;
     PromotionEnum(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
