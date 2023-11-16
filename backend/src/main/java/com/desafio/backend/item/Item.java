package com.desafio.backend.item;

import com.desafio.backend.product.Product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  private Product product;

  private Long amount;

}
