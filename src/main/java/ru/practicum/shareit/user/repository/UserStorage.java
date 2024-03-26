package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserStorage {

    User createUser(User user);

    User updateUser(long userId, User user);

    User getUserById(long id);

    void deleteUser(long id);

    List<User> getAllUsers();
}
