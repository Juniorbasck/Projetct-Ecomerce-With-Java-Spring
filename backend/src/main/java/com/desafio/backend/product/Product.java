package com.desafio.backend.product;

import com.desafio.backend.product.Enums.PromotionEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long price;

  private String name;

  @Column(nullable = true)
  private PromotionEnum promotion;
}
