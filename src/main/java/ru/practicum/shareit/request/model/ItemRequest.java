package ru.practicum.shareit.request.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class ItemRequest {

    private long id;

    private String description;

    @NotNull
    private User requester;

    @NotNull
    private LocalDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemRequest that = (ItemRequest) o;

        if (getId() != that.getId()) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null) {
            return false;
        }
        if (!getRequester().equals(that.getRequester())) {
            return false;
        }
        return getCreated().equals(that.getCreated());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getRequester().hashCode();
        result = 31 * result + getCreated().hashCode();
        return result;
    }
}
