package ru.practicum.shareit.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.EmailAlreadyExists;
import ru.practicum.shareit.exceptions.InputDataErrorException;
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

    @Override
    public User updateUser(long userId, User user) {
        User existingUser = getUserById(userId);

        if (existingUser == null) {
            throw new InputDataErrorException("User not found");
        }

        if (UserValidator.isEmailExists(user.getEmail()) && !existingUser.getEmail().equals(user.getEmail())) {
            throw new EmailAlreadyExists("Email already exists");
        }

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        userStorage.remove(userId);
        userStorage.put(userId, existingUser);

        return existingUser;
    }

    public User getUserById(long id) {
        return userStorage.values().stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static List<User> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }
}