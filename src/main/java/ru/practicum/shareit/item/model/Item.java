package ru.practicum.shareit.item.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table(name = "items", schema = "public")
@Entity
@Getter @Setter
public class Item {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "owner_id", nullable = false)
    @NotNull
    private long ownerId;

    @Column(name = "description")
    @NotBlank
    private String description;


    //@Transient
    @Column(name = "request_id")
    private long request;

    @Column(name = "is_available")
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
        if (getOwnerId() != item.getOwnerId()) {
            return false;
        }
        if (getRequest() != item.getRequest()) {
            return false;
        }
        if (!getName().equals(item.getName())) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(item.getDescription()) : item.getDescription() != null) {
            return false;
        }
        return getAvailable().equals(item.getAvailable());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + (int) (getOwnerId() ^ (getOwnerId() >>> 32));
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (int) (getRequest() ^ (getRequest() >>> 32));
        result = 31 * result + getAvailable().hashCode();
        return result;
    }
}
