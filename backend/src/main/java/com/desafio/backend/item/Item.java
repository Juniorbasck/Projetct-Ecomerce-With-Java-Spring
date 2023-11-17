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

  @ManyToOne(cascade = CascadeType.ALL)
  private Product product;

  private Long amount;

}
