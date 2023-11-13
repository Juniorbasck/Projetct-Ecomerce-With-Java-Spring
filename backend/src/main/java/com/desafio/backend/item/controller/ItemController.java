package com.desafio.backend.item.controller;

import com.desafio.backend.item.Item;
import com.desafio.backend.item.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public Item addItemInTheCard(@RequestBody Long cartId, Item item){
        return itemService.addItemToCart(cartId ,item);
    }
}