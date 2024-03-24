package ru.practicum.shareit.utils;

import lombok.extern.slf4j.Slf4j;
import ru.practicum.shareit.exceptions.EmailAlreadyExists;
import ru.practicum.shareit.exceptions.InputDataErrorException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserInMemoryStorage;

@Slf4j
public class UserValidator {

    public static boolean isEmailExists(String email) {
        for (User i : UserInMemoryStorage.getAllUsers()) {
            if (i.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMailPatternValid(String email) {
        String emailPattern = "^[a-zA-Z0-9._%-]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}$";
        return !email.matches(emailPattern);
    }

    public static void validateUser(User user) {
        if (isEmailExists(user.getEmail())) {
            throw new EmailAlreadyExists("this email address is already registered in the database");
        }
        if (isMailPatternValid(user.getEmail())) {
            throw new InputDataErrorException("Email does not match the pattern");
        }

    }

}