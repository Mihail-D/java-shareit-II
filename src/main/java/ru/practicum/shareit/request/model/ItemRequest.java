package ru.practicum.shareit.request.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requests", schema = "public")

public class ItemRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "requester_id", nullable = false)
    private long requestor;

    @Column(name = "created")
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

        if (id != that.id) {
            return false;
        }
        if (requestor != that.requestor) {
            return false;
        }
        if (!description.equals(that.description)) {
            return false;
        }
        return created.equals(that.created);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + description.hashCode();
        result = 31 * result + (int) (requestor ^ (requestor >>> 32));
        result = 31 * result + created.hashCode();
        return result;
    }
}
