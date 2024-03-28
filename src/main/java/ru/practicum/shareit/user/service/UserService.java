package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserStorage;

import java.util.List;

@Service
public class UserService {

    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User createUser(User user) {
        return userStorage.createUser(user);
    }

    public User updateUser(long userId, User user) {
        return userStorage.updateUser(userId, user);
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