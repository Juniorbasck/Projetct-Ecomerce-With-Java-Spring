package com.desafio.backend.product;

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
}
