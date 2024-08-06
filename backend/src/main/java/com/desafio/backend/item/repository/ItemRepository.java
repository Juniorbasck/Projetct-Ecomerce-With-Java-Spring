package com.desafio.backend.item.repository;

import com.desafio.backend.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
