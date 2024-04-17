package ru.practicum.shareit.request.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.service.ItemRequestService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static ru.practicum.shareit.util.Constant.HEADER_USER;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/requests")
public class ItemRequestController {

    ItemRequestService itemRequestService;

    @Autowired
    public ItemRequestController(ItemRequestService itemRequestService) {
        this.itemRequestService = itemRequestService;
    }

    @PostMapping()
    public ItemRequest createRequest(@RequestHeader(HEADER_USER) Long userId, @RequestBody @Valid ItemRequest itemRequest) {
        return itemRequestService.createItemRequest(userId, itemRequest);
    }

    @GetMapping("/{requestId}")
    public Optional<ItemRequestDto> getItemRequestByUserId(@RequestHeader(HEADER_USER) Long userId, @PathVariable long requestId) {
        return itemRequestService.getItemRequestByUserId(userId, requestId);
    }

    @GetMapping()
    public Optional<List<ItemRequestDto>> getItemRequestsByUserId(@RequestHeader(HEADER_USER) long userId) {
        return itemRequestService.getItemRequestsByUserId(userId);
    }
}
