package ru.practicum.shareit.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.EmailAlreadyExists;
import ru.practicum.shareit.exceptions.UserNotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.utils.ValidateUser;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ValidateUser validateUser;
    long userCounter = 0;

    @Autowired
    public UserService(UserRepository userRepository, ValidateUser validateUser) {
        this.userRepository = userRepository;
        this.validateUser = validateUser;
    }

    public User createUser(User user) {

        long userId = getNextUserId();

        validateUser.validateUser(user, userRepository.findAll());

        user.setId(userId);

        return userRepository.save(user);
    }

    public User updateUser(long userId, User user) {
        User existingUser = userRepository.getReferenceById(userId);

        if (existingUser == null) {
            throw new UserNotFoundException("User not found");
        }

        if (validateUser.isEmailExists(user.getEmail(), userRepository.findAll())
                && !existingUser.getEmail().equals(user.getEmail())) {
            throw new EmailAlreadyExists("Email already exists");
        }

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        userRepository.save(existingUser);

        return existingUser;
    }

    public UserDto getUserById(long userId) {
        User user = userRepository.getReferenceById(userId);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        return UserMapper.toUserDto(userRepository.getReferenceById(userId));
    }

    /*public void deleteUser(long userId) {
        userStorage.deleteUser(userId);
    }*/

    /*public List<UserDto> getAllUsers() {
        return userStorage.getAllUsers();
    }*/

    private long getNextUserId() {
        userCounter++;
        return userCounter;
    }
}
