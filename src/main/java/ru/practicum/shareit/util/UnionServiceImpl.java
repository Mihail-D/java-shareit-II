package ru.practicum.shareit.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UnionServiceImpl implements UnionService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    public void checkUser(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(User.class, "User id " + userId + " not found.");
        }
    }

    @Override
    public void checkItem(Long itemId) {

        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException(Item.class, "Item id " + itemId + " not found.");
        }
    }

    @Override
    public void checkBooking(Long bookingId) {

        if (!bookingRepository.existsById(bookingId)) {
            throw new NotFoundException(Booking.class, "Booking id " + bookingId + " not found.");
        }
    }

    @Override
    public void checkItemRequest(long userId, ItemRequest itemRequest) {
        checkUser(userId);

        if (itemRequest.getDescription() == null) {
            throw new ValidationException("description is empty");
        }

    }
}
