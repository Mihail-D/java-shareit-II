package ru.practicum.shareit.item.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;

@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {

    ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    public Item createItem(@RequestHeader("X-Sharer-User-Id") Long ownerId, @RequestBody Item item) {

        return itemService.createItem(ownerId, item);
    }


    @PatchMapping("/{itemId}")
    public Item updateItem(
            @RequestHeader("X-Sharer-User-Id") Long ownerId, @PathVariable long itemId, @RequestBody Item item
    ) {

        return itemService.updateItem(ownerId, itemId, item);
    }


   /* @GetMapping("/{itemId}")
    public Optional<ItemDto> getItemById(@PathVariable long itemId) {
        return itemService.getItemById(itemId);
    }*/

    /* @GetMapping()
   public Optional<List<ItemDto>> getItemsByUserId(@RequestHeader("X-Sharer-User-Id") long userId) {
       return itemService.getItemsByUserId(userId);
   }*/

/*    @GetMapping("/search")
    public Optional<List<ItemDto>> getItemByText(@RequestParam("text") String text) {
        return itemService.getItemByText(text);
    }*/


}
