package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemStorage {

    Item createItem(long ownerId, Item item);

    Item updateItem(long userId, long itemId, Item item);

    Optional<ItemDto> getItemById(long itemId);

    Optional<List<ItemDto>> getItemsByUserId(long userId);
}
