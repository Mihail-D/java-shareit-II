package ru.practicum.shareit.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.EmailAlreadyExists;
import ru.practicum.shareit.exceptions.InputDataErrorException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.utils.ValidateUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserInMemoryStorage implements UserStorage {

    private long id = 0;
    private final static Map<Long, UserDto> userStorage = new HashMap<>();
    private final ValidateUser validateUser = new ValidateUser();

    @Override
    public UserDto createUser(UserDto userDto) {
        validateUser.validateUser(userDto, getAllUsers());

        id++;
        userDto.setId(id);

        userStorage.put(userDto.getId(), userDto);

        return userDto;
    }

    @Override
    public UserDto updateUser(long userId, UserDto userDto) {
        UserDto existingUser = getUserById(userId);

        if (existingUser == null) {
            throw new InputDataErrorException("User not found");
        }

        if (validateUser.isEmailExists(userDto.getEmail(), getAllUsers()) && !existingUser.getEmail().equals(userDto.getEmail())) {
            throw new EmailAlreadyExists("Email already exists");
        }

        if (userDto.getName() != null) {
            existingUser.setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }

        userStorage.put(userId, existingUser);

        return existingUser;
    }

    @Override
    public UserDto getUserById(long id) {
        return userStorage.get(id);
    }

    @Override
    public void deleteUser(long id) {
        userStorage.remove(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }
}