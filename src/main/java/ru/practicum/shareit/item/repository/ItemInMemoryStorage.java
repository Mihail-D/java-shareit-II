package ru.practicum.shareit.item.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.utils.ValidateItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        id++;
        item.setId(id);

        validateItem.validateItem(item);

        itemStorage.put(id, item);

        return item;
    }

    public static List<Long> getUsersId() {
        return itemStorage.values().stream()
                .map(Item::getId)
                .collect(Collectors.toList());
    }
}
