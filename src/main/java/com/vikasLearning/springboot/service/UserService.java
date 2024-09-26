package com.vikasLearning.springboot.service;

import com.vikasLearning.springboot.dto.UserDto;
import com.vikasLearning.springboot.entity.User;

import java.util.List;


public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUser();

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);
}
