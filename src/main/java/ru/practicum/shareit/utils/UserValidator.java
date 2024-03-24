package ru.practicum.shareit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.service.UserService;

@Component
public class UserValidator {

    //private UserService userService;

/*    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public boolean isEmailExists(String email) {
        return userService.getUsersEmails().contains(email);
    }*/
}