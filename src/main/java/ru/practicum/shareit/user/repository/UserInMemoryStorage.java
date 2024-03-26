package ru.practicum.shareit.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.EmailAlreadyExists;
import ru.practicum.shareit.exceptions.InputDataErrorException;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserInMemoryStorage implements UserStorage {

    private long id = 0;
    private final static Map<Long, User> userStorage = new HashMap<>();

    private boolean isEmailExists(String email) {
        for (User i : getAllUsers()) {
            if (i.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMailPatternValid(String email) {
        String emailPattern = "^[a-zA-Z0-9._%-]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}$";
        return !email.matches(emailPattern);
    }

    private void validateUser(User user) {
        if (isEmailExists(user.getEmail())) {
            throw new EmailAlreadyExists("this email address is already registered in the database");
        }
        if (isMailPatternValid(user.getEmail())) {
            throw new InputDataErrorException("Email does not match the pattern");
        }
    }

    @Override
    public User createUser(User user) {
        validateUser(user);

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

        if (isEmailExists(user.getEmail()) && !existingUser.getEmail().equals(user.getEmail())) {
            throw new EmailAlreadyExists("Email already exists");
        }

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        userStorage.put(userId, existingUser);

        return existingUser;
    }

    @Override
    public User getUserById(long id) {
        return userStorage.values().stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteUser(long id) {
        userStorage.remove(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }
}