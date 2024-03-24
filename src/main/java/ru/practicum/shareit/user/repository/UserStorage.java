package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.Map;

public interface UserStorage {

    User createUser(User user);

    Map<String, User> getAllUsers();
}
