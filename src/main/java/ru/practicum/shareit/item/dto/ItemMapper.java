package ru.practicum.shareit.item.dto;

import ru.practicum.shareit.exceptions.InputDataErrorException;
import ru.practicum.shareit.item.model.Item;

public class ItemMapper {

    public static ItemDto toItemDto(Item item) {
        long requestId;

        try {
            requestId = item.getRequest().getId();
        } catch (Exception e) {
            throw new InputDataErrorException("such id does not exist");
        }

        return new ItemDto(
                requestId,
                item.getName(),
                item.getDescription(),
                item.isAvailable()
        );
    }
}
