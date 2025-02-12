package com.codecool.server.service;

import com.codecool.server.DTO.user.UserDTO;
import com.codecool.server.mapper.UserMapper;
import com.codecool.server.model.UserEntity;
import com.codecool.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
private final UserRepository userRepository;
private final UserMapper userMapper;

@Autowired
public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
}
public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream().map(userMapper::userEntityToUserDTO).collect(Collectors.toList());
}
}
