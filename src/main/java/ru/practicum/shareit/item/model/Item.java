package ru.practicum.shareit.item.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Item {

    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private User owner;

    @NotBlank
    private String description;

    @NotNull
    private ItemRequest request;

    @NotNull
    private Boolean available;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        if (getId() != item.getId()) {
            return false;
        }
        if (!getName().equals(item.getName())) {
            return false;
        }
        if (!getOwner().equals(item.getOwner())) {
            return false;
        }
        if (!getDescription().equals(item.getDescription())) {
            return false;
        }
        if (!getRequest().equals(item.getRequest())) {
            return false;
        }
        return getAvailable().equals(item.getAvailable());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + getOwner().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getRequest().hashCode();
        result = 31 * result + getAvailable().hashCode();
        return result;
    }
}
