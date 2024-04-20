package ru.practicum.shareit.booking.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.booking.enums.Status;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingOutDto {

    private Long id;

    private LocalDateTime start;

    private LocalDateTime end;

    private Status status;

    private UserDto booker;

    private ItemDto item;

    public BookingOutDto(Long id, LocalDateTime start, LocalDateTime end, Status status, UserDto booker, ItemDto item) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.status = status;
        this.booker = booker;
        this.item = item;
    }
}
