package ru.practicum.shareit.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.EmailAlreadyExists;
import ru.practicum.shareit.exceptions.InputDataErrorException;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserInMemoryStorage implements UserStorage {

    private long id = 0;
    private final static Map<Long, UserDto> userStorage = new HashMap<>();

    private boolean isEmailExists(String email) {
        for (UserDto i : getAllUsers()) {
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

    private void validateUser(UserDto userDto) {
        if (isEmailExists(userDto.getEmail())) {
            throw new EmailAlreadyExists("this email address is already registered in the database");
        }
        if (isMailPatternValid(userDto.getEmail())) {
            throw new InputDataErrorException("Email does not match the pattern");
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        validateUser(userDto);

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

        if (isEmailExists(userDto.getEmail()) && !existingUser.getEmail().equals(userDto.getEmail())) {
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