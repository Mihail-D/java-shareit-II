package ru.practicum.shareit.item.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Item {

    private long id;

    @NotBlank
    private String name;
    @NotBlank

    private String owner;


    private String description;

    private String request;

    private boolean available;

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
        if (isAvailable() != item.isAvailable()) {
            return false;
        }
        if (!getName().equals(item.getName())) {
            return false;
        }
        if (!getOwner().equals(item.getOwner())) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(item.getDescription()) : item.getDescription() != null) {
            return false;
        }
        return getRequest() != null ? getRequest().equals(item.getRequest()) : item.getRequest() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + getOwner().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getRequest() != null ? getRequest().hashCode() : 0);
        result = 31 * result + (isAvailable() ? 1 : 0);
        return result;
    }
}
