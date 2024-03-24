package ru.practicum.shareit.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.EmailAlreadyExists;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.utils.UserValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserInMemoryStorage implements UserStorage {

    private long id = 0;
    private final static Map<Long, User> userStorage = new HashMap<>();

    @Override
    public User createUser(User user) {
        UserValidator.validateUser(user);

        id++;
        user.setId(id);

        userStorage.put(user.getId(), user);

        return user;
    }

    public static List<User> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }
}