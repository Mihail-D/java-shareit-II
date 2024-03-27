package ru.practicum.shareit.item.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exceptions.SharerUserIdException;
import ru.practicum.shareit.exceptions.UserNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.item.utils.ValidateItem;

@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {

    ItemService itemService;
    ValidateItem validateItem;

    @Autowired
    public ItemController(ItemService itemService, ValidateItem validateItem) {
        this.itemService = itemService;
        this.validateItem = validateItem;
    }

    @PostMapping()
    public Item createItem(@RequestHeader("X-Sharer-User-Id") Long userId, @RequestBody Item item) {

        if (validateItem.isUserIdNotNull(userId)) {
            throw new SharerUserIdException("missing userId parameter in request header");
        }
        if (!validateItem.isUserIdUnknown(userId)) {
            throw new UserNotFoundException("user not found");
        }

        return itemService.createItem(item);
    }
}
