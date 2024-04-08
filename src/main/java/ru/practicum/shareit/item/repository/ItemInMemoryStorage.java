package ru.practicum.shareit.item.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.utils.ValidateItem;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Slf4j
@Repository
public class ItemInMemoryStorage {

    private long id = 0;
    private final Map<Long, Item> itemStorage = new HashMap<>();
    ValidateItem validateItem;

    @Autowired
    public ItemInMemoryStorage(ValidateItem validateItem) {
        this.validateItem = validateItem;
    }


    public Item createItem(long ownerId, Item item) {

        validateItem.validateItemForCreate(ownerId, item);

        id++;
        item.setId(id);

        User owner = new User();
        owner.setId(ownerId);
        item.setOwnerId(ownerId);

        itemStorage.put(id, item);

        return item;
    }


    public Item updateItem(long ownerId, long itemId, Item item) {

        Item existingItem = itemStorage.get(itemId);

        validateItem.validateItemForUpdate(ownerId, existingItem);

        if (item.getAvailable() != null) {
            existingItem.setAvailable(item.getAvailable());
        }
        if (item.getName() != null) {
            existingItem.setName(item.getName());
        }
        if (item.getDescription() != null) {
            existingItem.setDescription(item.getDescription());
        }

        itemStorage.put(existingItem.getId(), existingItem);

        return existingItem;
    }


    public Optional<ItemDto> getItemById(long itemId) {
        return Optional.of(ItemMapper.toItemDto(itemStorage.get(itemId)));
    }


    public Optional<List<ItemDto>> getItemsByUserId(long userId) {
        List<ItemDto> itemsList = new ArrayList<>();

        for (Item i : itemStorage.values()) {
            if (i.getOwnerId() == userId) {
                itemsList.add(ItemMapper.toItemDto(i));
            }
        }

        return Optional.of(itemsList);
    }


    public Optional<List<ItemDto>> getItemByText(String text) {
        List<ItemDto> itemsList = new ArrayList<>();

        if (text.isBlank()) {
            return Optional.of(itemsList);
        }

        for (Item i : itemStorage.values()) {
            if (!i.getAvailable()) {
                continue;
            }
            if (i.getName().toLowerCase().contains(text.toLowerCase())
                    || i.getDescription().toLowerCase().contains(text.toLowerCase())) {
                itemsList.add(ItemMapper.toItemDto(i));
            }
        }

        return Optional.of(itemsList);
    }
}
