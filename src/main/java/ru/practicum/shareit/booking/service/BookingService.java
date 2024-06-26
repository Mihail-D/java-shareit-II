package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingOutDto;

import java.util.List;

public interface BookingService {

    BookingOutDto addBooking(BookingDto bookingDto, long userId);

    BookingOutDto approveBooking(long userId, long bookingId, Boolean approved);

    BookingOutDto getBookingById(long userId, long bookingId);

    List<BookingOutDto> getAllBookingsByBookerId(long userId, String state, Integer from, Integer size);

    List<BookingOutDto> getAllBookingsForAllItemsByOwnerId(long userId, String state, Integer from, Integer size);
}
