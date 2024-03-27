package ru.practicum.shareit.item.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.ItemDataInputErrorException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.utils.ValidateItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ItemInMemoryStorage implements ItemStorage {

    private long id = 0;
    private final static Map<Long, Item> itemStorage = new HashMap<>();
    ValidateItem validateItem;

    @Autowired
    public ItemInMemoryStorage(ValidateItem validateItem) {
        this.validateItem = validateItem;
    }

    @Override
    public Item createItem(Item item) {

        if (validateItem.isItemAvailable(item)) {
            throw new ItemDataInputErrorException("item availability status is missing");
        }

        id++;
        item.setId(id);

        itemStorage.put(id, item);

        log.warn("!!!!! " + itemStorage.keySet());

        return item;
    }

    public static List<Item> getUsersId() {
        return new ArrayList<>(itemStorage.values());
    }
}
