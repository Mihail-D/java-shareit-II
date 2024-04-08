package ru.practicum.shareit.item.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.utils.ValidateItem;

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

    public Optional<ItemDto> getItemById(long itemId) {
        return Optional.of(ItemMapper.toItemDto(itemRepository.getReferenceById(itemId)));
    }

   /* public Optional<List<ItemDto>> getItemsByUserId(long userId) {
        return itemStorage.getItemsByUserId(userId);
    }*/

/*    public Optional<List<ItemDto>> getItemByText(String text) {
        return itemStorage.getItemByText(text);
    }*/

    private long getNextItemId() {
        itemCounter++;
        return itemCounter;
    }
}
