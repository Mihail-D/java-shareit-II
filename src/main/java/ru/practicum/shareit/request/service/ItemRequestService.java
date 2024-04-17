package ru.practicum.shareit.request.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.ItemRequestRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ItemRequestService {

    ItemRequestRepository itemRequestRepository;

    @Autowired
    public ItemRequestService(ItemRequestRepository itemRequestRepository) {
        this.itemRequestRepository = itemRequestRepository;
    }

    public ItemRequest createItemRequest(long userId, ItemRequest itemRequest) {
        itemRequest.setRequestor(userId);
        itemRequest.setCreated(LocalDateTime.now());
        return itemRequestRepository.save(itemRequest);
    }
}
