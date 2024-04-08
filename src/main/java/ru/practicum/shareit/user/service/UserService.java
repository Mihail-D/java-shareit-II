package ru.practicum.shareit.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.repository.UserStorage;
import ru.practicum.shareit.user.utils.ValidateUser;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ValidateUser validateUser;

    @Autowired
    public UserService(UserRepository userRepository, ValidateUser validateUser) {
        this.userRepository = userRepository;
        this.validateUser = validateUser;
    }

    public User createUser(User user) {
        validateUser.validateUser(user, userRepository.findAll());
        return userRepository.save(user);
    }

    /*public User updateUser(long userId, User user) {
        return userStorage.updateUser(userId, user);
    }*/

    /*public UserDto getUserById(long userId) {
        return userStorage.getUserById(userId);
    }*/

    /*public void deleteUser(long userId) {
        userStorage.deleteUser(userId);
    }*/

    /*public List<UserDto> getAllUsers() {
        return userStorage.getAllUsers();
    }*/
}
