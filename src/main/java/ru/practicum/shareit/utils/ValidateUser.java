package ru.practicum.shareit.utils;

import ru.practicum.shareit.exceptions.EmailAlreadyExists;
import ru.practicum.shareit.exceptions.InputDataErrorException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

public class ValidateUser {

    public boolean isEmailExists(String email, List<UserDto> users) {
        for (UserDto i : users) {
            if (i.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMailPatternValid(String email) {
        String emailPattern = "^[a-zA-Z0-9._%-]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}$";
        return !email.matches(emailPattern);
    }

    public void validateUser(User userDto, List<UserDto> users) {
        if (isEmailExists(userDto.getEmail(), users)) {
            throw new EmailAlreadyExists("this email address is already registered in the database");
        }
        if (isMailPatternValid(userDto.getEmail())) {
            throw new InputDataErrorException("Email does not match the pattern");
        }
    }
}