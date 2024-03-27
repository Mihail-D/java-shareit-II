package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class ItemDto {

    private long id;

    @NotBlank
    private String name;

    private String description;

    private boolean available;

}
