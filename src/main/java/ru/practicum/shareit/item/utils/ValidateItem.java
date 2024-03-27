package ru.practicum.shareit.item.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemInMemoryStorage;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ValidateItem {

    public boolean isUserIdNotNull(Long userId) {
        return userId == null;
    }

    public boolean isUserIdUnknown(long userId) {
        List<Item> userIds = ItemInMemoryStorage.getUsersId();

        if (userIds.isEmpty()) {
            return true;
        }

        Optional<Item> itemOptional = userIds.stream()
                .filter(i -> userId == i.getId())
                .findFirst();

        return itemOptional.isEmpty();
    }

    public boolean isItemAvailable(Item item) {
        Boolean isAvailable = item.getAvailable();
        return isAvailable == null;
    }
}
