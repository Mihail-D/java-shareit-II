package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserStorage {

    User createUser(User user);

    User updateUser(long userId, User user);

    UserDto getUserById(long id);

    void deleteUser(long id);

    List<UserDto> getAllUsers();
}
