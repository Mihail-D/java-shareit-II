package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.repository.UserStorage;

import java.util.List;

@Service
public class UserService {

    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public UserDto createUser(UserDto userDto) {
        return userStorage.createUser(userDto);
    }

    public UserDto updateUser(long userId, UserDto userDto) {
        return userStorage.updateUser(userId, userDto);
    }

    public UserDto getUserById(long userId) {
        return userStorage.getUserById(userId);
    }

    public void deleteUser(long userId) {
        userStorage.deleteUser(userId);
    }

    public List<UserDto> getAllUsers() {
        return userStorage.getAllUsers();
    }
}