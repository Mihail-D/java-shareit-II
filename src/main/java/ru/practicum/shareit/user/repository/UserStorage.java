package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserStorage {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(long userId, UserDto userDto);

    UserDto getUserById(long id);

    void deleteUser(long id);

    List<UserDto> getAllUsers();
}
