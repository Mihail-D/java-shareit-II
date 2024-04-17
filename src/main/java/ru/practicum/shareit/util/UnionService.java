package ru.practicum.shareit.util;

import ru.practicum.shareit.request.model.ItemRequest;

public interface UnionService {

    void checkUser(Long userId);

    void checkItem(Long itemId);

    void checkBooking(Long booking);

    void checkItemRequest(long userId, ItemRequest itemRequest);
}
