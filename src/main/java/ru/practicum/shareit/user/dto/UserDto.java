package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
}
