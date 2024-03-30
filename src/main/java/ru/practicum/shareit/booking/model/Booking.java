package ru.practicum.shareit.booking.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.utils.Status;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class Booking {
    private long id;

    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;
    @NotNull
    private Item item;
    @NotNull
    private User booker;
    @NotNull
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Booking booking = (Booking) o;

        if (getId() != booking.getId()) {
            return false;
        }
        if (!getStart().equals(booking.getStart())) {
            return false;
        }
        if (!getEnd().equals(booking.getEnd())) {
            return false;
        }
        if (!getItem().equals(booking.getItem())) {
            return false;
        }
        if (!getBooker().equals(booking.getBooker())) {
            return false;
        }
        return getStatus() == booking.getStatus();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getStart().hashCode();
        result = 31 * result + getEnd().hashCode();
        result = 31 * result + getItem().hashCode();
        result = 31 * result + getBooker().hashCode();
        result = 31 * result + getStatus().hashCode();
        return result;
    }
}
