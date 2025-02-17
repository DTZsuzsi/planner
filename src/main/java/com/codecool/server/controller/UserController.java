package com.codecool.server.controller;

import com.codecool.server.DTO.user.UserDTO;
import com.codecool.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

@GetMapping
    public UserDTO getUserById(@RequestParam long id) {
        return userService.getUserById(id);
}
@PostMapping
    public long addUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
}

@PatchMapping
    public long updateUser(@RequestBody UserDTO userDTO) {
        return userService.modifyUser(userDTO);
}

@DeleteMapping
    public boolean deleteUserById(@RequestParam long id) {
        return userService.deleteUser(id);
}
}
