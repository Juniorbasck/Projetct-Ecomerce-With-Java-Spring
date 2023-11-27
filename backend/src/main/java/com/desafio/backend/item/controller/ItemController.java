package com.desafio.backend.item.controller;

import com.desafio.backend.item.Item;
import com.desafio.backend.item.models.ItemRequestDTO;
import com.desafio.backend.item.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/{cartId}")
    public Item addItemInTheCard(@PathVariable long cartId, @RequestBody ItemRequestDTO itemRequestDTO){
        return itemService.addItemToCart(
                cartId,
                itemRequestDTO.getProductId(),
                itemRequestDTO.getAmount(),
                itemRequestDTO.getPromotion());
    }
}