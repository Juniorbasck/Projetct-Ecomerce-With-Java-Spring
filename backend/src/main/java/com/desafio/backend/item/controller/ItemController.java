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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/{cartId}")
    public Item addItemInTheCard(@PathVariable long cartId, @RequestBody ItemRequest itemRequest){
        return itemService.addItemToCart(
                cartId,
                itemRequest.getProductId(),
                itemRequest.getAmount());
    }
}