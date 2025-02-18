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
private final UserMapper userMapper=UserMapper.INSTANCE;

@Autowired
public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;

}
public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream().map(userMapper::userEntityToUserDTO).collect(Collectors.toList());
}

public UserDTO getUserById(long id) {
    return userRepository.findById(id).map(userMapper::userEntityToUserDTO).orElse(null);
}

public long createUser(UserDTO userDTO) {
    UserEntity userEntity = userMapper.userDTOToUserEntity(userDTO);
    return userRepository.save(userEntity).getId();
}

public long modifyUser(UserDTO userDTO) {
    UserEntity userEntity = userRepository.findById(userDTO.id()).orElse(null);
    if (userEntity != null) {
        userEntity.setEmail(userDTO.email());
        userEntity.setPassword(userDTO.password());
        userEntity.setFirstName(userDTO.firstName());
        userEntity.setLastName(userDTO.lastName());
        userEntity.setUsername(userDTO.username());
        return userRepository.save(userEntity).getId();

    }
    return 0;
}

public boolean deleteUser(long id) {
    UserEntity userEntity = userRepository.findById(id).orElse(null);
    if (userEntity != null) {
        userRepository.delete(userEntity);
        return true;
    }
 return false;
}
}
