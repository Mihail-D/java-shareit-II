package ru.practicum.shareit.user.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PatchMapping("/{userId}")
    public User updateUser(@Valid @PathVariable long userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @GetMapping("/{userId}")
    public User getUserById(@Valid @PathVariable long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}