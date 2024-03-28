package ru.practicum.shareit.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.EmailAlreadyExists;
import ru.practicum.shareit.exceptions.UserNotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.utils.ValidateUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserInMemoryStorage implements UserStorage {

    private long id = 0;
    private final static Map<Long, User> userStorage = new HashMap<>();
    private final ValidateUser validateUser = new ValidateUser();

    @Override
    public User createUser(User user) {
        validateUser.validateUser(user, getAllUsers());

        id++;
        user.setId(id);

        userStorage.put(user.getId(), user);

        return user;
    }

    @Override
    public User updateUser(long userId, User user) {
        User existingUser = userStorage.get(userId);

        if (existingUser == null) {
            throw new UserNotFoundException("User not found");
        }

        if (validateUser.isEmailExists(user.getEmail(), getAllUsers())
                && !existingUser.getEmail().equals(user.getEmail())) {
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
    public UserDto getUserById(long id) {
        User user = userStorage.get(id);
        if (user == null) {
            return null;
        }
        return userToDto(user);
    }

    @Override
    public void deleteUser(long id) {
        userStorage.remove(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userStorage.values()) {
            userDtoList.add(userToDto(user));
        }
        return userDtoList;
    }

    private UserDto userToDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}