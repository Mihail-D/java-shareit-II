package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

public interface UserStorage {

    User createUser(User user);

    User updateUser(long userId, User user);

    User getUserById(long id);
}
