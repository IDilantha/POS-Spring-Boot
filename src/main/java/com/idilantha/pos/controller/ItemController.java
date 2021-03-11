package com.idilantha.pos.controller;

import java.util.ArrayList;
import java.util.List;

import com.idilantha.pos.dto.ItemDto;
import com.idilantha.pos.model.Item;
import com.idilantha.pos.repository.ItemRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/Item")
@CrossOrigin
@RestController
public class ItemController{

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/CreateItem")
    public ResponseEntity<String> createItem(@RequestBody ItemDto itemDto) {
        Item item = new Item();
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setQuantity(itemDto.getQuantity());

        Item saveAndFlushItem = itemRepository.saveAndFlush(item);

        if (saveAndFlushItem != null) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/GetAllItems")
    List<ItemDto> getAllItems() {
        List<ItemDto> findAllItems = new ArrayList<>();
        List<Item> findAll = itemRepository.findAll();

        for (Item item : findAll) {
            ItemDto itemDto = new ItemDto();
            ItemDto itemD = convertToItemDto(item);
            findAllItems.add(itemD);
        }
        return findAllItems;
    }

    public ItemDto convertToItemDto(Item itemss) {
        ItemDto map = modelMapper.map(itemss, ItemDto.class);
        return map;
    }
}
