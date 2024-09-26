package com.vikasLearning.springboot.mapper;

import com.vikasLearning.springboot.dto.UserDto;
import com.vikasLearning.springboot.entity.User;

public class UserMapper {

    //Convert User JPA entity into UserDto
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(), user.getEmail()
        );
        return userDto;
    }

    //Convert UserDto into User JPA Entity
    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }
}
