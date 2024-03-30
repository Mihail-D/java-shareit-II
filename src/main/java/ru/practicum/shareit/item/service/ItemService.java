package ru.practicum.shareit.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemStorage;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    ItemStorage itemStorage;

    @Autowired
    public ItemService(ItemStorage itemStorage) {
        this.itemStorage = itemStorage;
    }

    public Item createItem(long ownerId, Item item) {
        return itemStorage.createItem(ownerId, item);
    }

    public Item updateItem(long ownerId, long itemId, Item item) {
        return itemStorage.updateItem(ownerId, itemId, item);
    }

    public Optional<ItemDto> getItemById(long itemId) {
        return itemStorage.getItemById(itemId);
    }

    public Optional<List<ItemDto>> getItemsByUserId(long userId) {
        return itemStorage.getItemsByUserId(userId);
    }

    public Optional<List<ItemDto>> getItemByText(String text) {
        return itemStorage.getItemByText(text);
    }
}
