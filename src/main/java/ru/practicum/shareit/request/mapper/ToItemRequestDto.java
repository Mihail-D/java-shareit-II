package ru.practicum.shareit.request.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.time.LocalDate;
import java.util.Optional;

@UtilityClass
public class ToItemRequestDto {

    public static Optional<ItemRequestDto> toItemRequestDto(Optional<ItemRequest> itemRequestOptional) {
        return itemRequestOptional.map(itemRequest -> ItemRequestDto.builder()
                .id(itemRequest.getId())
                .description(itemRequest.getDescription())
                .requestor(itemRequest.getRequestor())
                .created(LocalDate.from(itemRequest.getCreated()))
                .build());
    }
}
