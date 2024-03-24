package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class ItemDto {

    @NotNull
    private long requestId;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private boolean available;

}
