package ru.practicum.shareit.request.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.mapper.ToItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.ItemRequestRepository;
import ru.practicum.shareit.util.UnionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemRequestService {

    UnionService unionService;
    ItemRequestRepository itemRequestRepository;

    @Autowired
    public ItemRequestService(UnionService unionService, ItemRequestRepository itemRequestRepository) {
        this.unionService = unionService;
        this.itemRequestRepository = itemRequestRepository;
    }

    public ItemRequest createItemRequest(long userId, ItemRequest itemRequest) {
        unionService.checkItemRequest(userId, itemRequest);
        itemRequest.setRequestor(userId);
        itemRequest.setCreated(LocalDateTime.now());
        return itemRequestRepository.save(itemRequest);
    }

    public Optional<ItemRequestDto> getItemRequestByUserId(long userId, long requestId) {
        return ToItemRequestDto.toItemRequestDto(itemRequestRepository.findByRequestorAndId(userId, requestId));
    }

    public Optional<List<ItemRequestDto>> getItemRequestsByUserId(long userId) {
        unionService.checkUser(userId);
        List<ItemRequest> itemRequests = itemRequestRepository.findByRequestor(userId, Sort.by(Sort.Direction.DESC, "created"));
        return Optional.of(ToItemRequestDto.toItemRequestDtoList(itemRequests));
    }
}
