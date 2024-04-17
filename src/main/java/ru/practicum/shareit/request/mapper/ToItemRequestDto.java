package ru.practicum.shareit.request.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public static ItemRequestDto toItemRequestDto(ItemRequest itemRequest) {
        return ItemRequestDto.builder()
                .id(itemRequest.getId())
                .description(itemRequest.getDescription())
                .requestor(itemRequest.getRequestor())
                .created(LocalDate.from(itemRequest.getCreated()))
                .build();
    }

    public static List<ItemRequestDto> toItemRequestDtoList(List<ItemRequest> itemRequestList) {
        return itemRequestList.stream()
                .map(itemRequest -> ItemRequestDto.builder()
                        .id(itemRequest.getId())
                        .description(itemRequest.getDescription())
                        .requestor(itemRequest.getRequestor())
                        .created(LocalDate.from(itemRequest.getCreated()))
                        .build())
                .collect(Collectors.toList());
    }
}
