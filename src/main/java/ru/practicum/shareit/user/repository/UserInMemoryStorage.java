package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.EmailAlreadyExists;
import ru.practicum.shareit.user.model.User;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserInMemoryStorage implements UserStorage {

    private long id = 0;
    private final Map<String, User> userStorage = new HashMap<>();

    @Override
    public User createUser(User user) {
        if (userStorage.containsKey(user.getEmail())) {
            throw new EmailAlreadyExists("this email address is already registered in the database");
        }

        id++;
        user.setId(id);

        userStorage.put(user.getEmail(), user);

        return user;
    }

    @Override
    public Map<String, User> getAllUsers() {
        return userStorage;
    }
}