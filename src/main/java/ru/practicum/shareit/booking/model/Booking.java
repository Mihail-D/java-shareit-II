package ru.practicum.shareit.booking.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.utils.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name="bookings", schema = "public")
@Entity
@Getter @Setter
public class Booking {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private long id;

    @Column(name = "start_date")
    @NotNull
    private LocalDateTime start;

    @Column(name = "end_date")
    @NotNull
    private LocalDateTime end;

    @Column(name = "item_id")
    @NotNull
    private long itemId;

    @Column(name = "booker_id")
    @NotNull
    private long booker_id;

    @Column(name = "status")
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
        if (getItemId() != booking.getItemId()) {
            return false;
        }
        if (getBooker_id() != booking.getBooker_id()) {
            return false;
        }
        if (!getStart().equals(booking.getStart())) {
            return false;
        }
        if (!getEnd().equals(booking.getEnd())) {
            return false;
        }
        return getStatus() == booking.getStatus();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getStart().hashCode();
        result = 31 * result + getEnd().hashCode();
        result = 31 * result + (int) (getItemId() ^ (getItemId() >>> 32));
        result = 31 * result + (int) (getBooker_id() ^ (getBooker_id() >>> 32));
        result = 31 * result + getStatus().hashCode();
        return result;
    }
}
