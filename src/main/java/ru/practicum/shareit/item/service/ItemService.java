package ru.practicum.shareit.item.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.ItemDataInputErrorException;
import ru.practicum.shareit.exceptions.ItemNotFoundException;
import ru.practicum.shareit.exceptions.UserNotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.utils.ValidateItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemService {

    ItemRepository itemRepository;
    ValidateItem validateItem;
    long itemCounter = 0;

    @Autowired
    public ItemService(ItemRepository itemRepository, ValidateItem validateItem) {
        this.itemRepository = itemRepository;
        this.validateItem = validateItem;
    }

    public Item createItem(long ownerId, Item item) {
        validateItem.validateItemForCreate(ownerId, item);
        long itemId = getNextItemId();
        item.setId(itemId);
        item.setOwnerId(ownerId);

        return itemRepository.save(item);
    }

    public Item updateItem(long ownerId, long itemId, Item item) {
        Item existingItem = itemRepository.getReferenceById(itemId);
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

        return itemRepository.save(existingItem);
    }

    public ItemDto getItemById(long itemId) {
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
            throw new ItemNotFoundException("Item with id " + itemId + " not found");
        }
        return ItemMapper.toItemDto(item);
    }

    public Optional<List<ItemDto>> getItemsByUserId(long userId) {

        List<ItemDto> itemsList = new ArrayList<>();

        for (Item i : itemRepository.findAll()) {
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

        for (Item i : itemRepository.findAll()) {
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

    private long getNextItemId() {
        itemCounter++;
        return itemCounter;
    }
}
