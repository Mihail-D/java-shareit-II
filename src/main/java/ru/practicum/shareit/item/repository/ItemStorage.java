package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

public interface ItemStorage {

    Item createItem(long ownerId, Item item);

    Item updateItem(long userId, long itemId, Item item);
}
