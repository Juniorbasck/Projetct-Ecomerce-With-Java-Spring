package com.desafio.backend.item.Service;

import com.desafio.backend.item.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
