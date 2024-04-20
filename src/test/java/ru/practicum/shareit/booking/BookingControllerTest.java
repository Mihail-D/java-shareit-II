package ru.practicum.shareit.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.practicum.shareit.booking.controller.BookingController;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingOutDto;
import ru.practicum.shareit.booking.enums.Status;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddBookingSuccessfully() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(bookingService.addBooking(any(BookingDto.class), anyLong()))
                .thenReturn(new BookingOutDto(1L, LocalDateTime.now(), LocalDateTime.now(), Status.APPROVED, new UserDto(1L, "login", "email"), new ItemDto(
                        1L,
                        "Item Name",
                        "Item Description",
                        true,
                        null,
                        null,
                        null,
                        1L
                )));

        ResponseEntity<BookingOutDto> responseEntity = bookingController.addBooking(1L, new BookingDto(1L, LocalDateTime.now(), LocalDateTime.now(), Status.APPROVED));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldApproveBookingSuccessfully() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(bookingService.approveBooking(anyLong(), anyLong(), anyBoolean()))
                .thenReturn(new BookingOutDto(1L, LocalDateTime.now(), LocalDateTime.now(), Status.APPROVED, new UserDto(1L, "login", "email"), new ItemDto(
                        1L,
                        "Item Name",
                        "Item Description",
                        true,
                        null,
                        null,
                        null,
                        1L
                )));

        ResponseEntity<BookingOutDto> responseEntity = bookingController.approveBooking(1L, 1L, true);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldGetBookingByIdSuccessfully() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(bookingService.getBookingById(anyLong(), anyLong()))
                .thenReturn(new BookingOutDto(1L, LocalDateTime.now(), LocalDateTime.now(), Status.APPROVED, new UserDto(1L, "login", "email"), new ItemDto(
                        1L,
                        "Item Name",
                        "Item Description",
                        true,
                        null,
                        null,
                        null,
                        1L
                )));

        ResponseEntity<BookingOutDto> responseEntity = bookingController.getBookingById(1L, 1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldGetAllBookingsByBookerIdSuccessfully() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(bookingService.getAllBookingsByBookerId(anyLong(), anyString(), anyInt(), anyInt()))
                .thenReturn(List.of(new BookingOutDto(1L, LocalDateTime.now(), LocalDateTime.now(), Status.APPROVED, new UserDto(1L, "login", "email"), new ItemDto(
                        1L,
                        "Item Name",
                        "Item Description",
                        true,
                        null,
                        null,
                        null,
                        1L
                ))));

        ResponseEntity<List<BookingOutDto>> responseEntity = bookingController.getAllBookingsByBookerId(1L, "ALL", 0, 10);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldGetAllBookingsForAllItemsByOwnerIdSuccessfully() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(bookingService.getAllBookingsForAllItemsByOwnerId(anyLong(), anyString(), anyInt(), anyInt()))
                .thenReturn(List.of(new BookingOutDto(1L, LocalDateTime.now(), LocalDateTime.now(), Status.APPROVED, new UserDto(1L, "login", "email"), new ItemDto(
                        1L,
                        "Item Name",
                        "Item Description",
                        true,
                        null,
                        null,
                        null,
                        1L
                ))));

        ResponseEntity<List<BookingOutDto>> responseEntity = bookingController.getAllBookingsForAllItemsByOwnerId(1L, "ALL", 0, 10);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
