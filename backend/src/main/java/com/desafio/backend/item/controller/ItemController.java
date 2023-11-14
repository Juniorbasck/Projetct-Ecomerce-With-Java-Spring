package com.desafio.backend.item.controller;

import com.desafio.backend.item.Item;
import com.desafio.backend.item.service.ItemRequest;
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
    public Item addItemInTheCard(@RequestBody ItemRequest itemRequest){
        return itemService.addItemToCart(
                itemRequest.getCartId(),
                itemRequest.getProductId(),
                itemRequest.getAmount());
    }
}