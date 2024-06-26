package ru.practicum.shareit.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserDto {

    private Long id;

    @NotNull(message = "Login cannot be empty or contain spaces.")
    @NotBlank(message = "Login cannot be empty or contain spaces.")
    private String name;

    @NotNull(message = "Email cannot be empty")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must contain the character @")
    private String email;

    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
