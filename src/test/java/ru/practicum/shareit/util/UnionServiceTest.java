package ru.practicum.shareit.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.repository.ItemRequestRepository;
import ru.practicum.shareit.user.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UnionServiceTest {

    @Autowired
    private UnionService unionService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private ItemRequestRepository itemRequestRepository;

    @Test
    void checkUser() {
        when(userRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> unionService.checkUser(1L));
    }

    @Test
    void checkItem() {
        when(itemRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> unionService.checkItem(1L));
    }

    @Test
    void checkBooking() {
        when(bookingRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> unionService.checkBooking(1L));
    }

    @Test
    void checkRequest() {
        when(itemRequestRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> unionService.checkRequest(1L));
    }

    @Test
    void checkPageSize() {
        assertThrows(ValidationException.class, () -> unionService.checkPageSize(0, 0));
        assertThrows(ValidationException.class, () -> unionService.checkPageSize(5, -5));
        assertThrows(ValidationException.class, () -> unionService.checkPageSize(5, 0));
        assertThrows(ValidationException.class, () -> unionService.checkPageSize(-5, 5));
    }
}