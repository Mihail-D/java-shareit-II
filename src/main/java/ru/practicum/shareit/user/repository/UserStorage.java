package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Map;

public interface UserStorage {

    User createUser(User user);
}
