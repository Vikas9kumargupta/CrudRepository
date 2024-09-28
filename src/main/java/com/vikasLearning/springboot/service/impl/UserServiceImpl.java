package com.vikasLearning.springboot.service.impl;

import com.vikasLearning.springboot.dto.UserDto;
import com.vikasLearning.springboot.exception.EmailAlreadyExistsException;
import com.vikasLearning.springboot.exception.ResourceNotFoundException;
import com.vikasLearning.springboot.mapper.AutoUserMapper;
import com.vikasLearning.springboot.mapper.UserMapper;
import com.vikasLearning.springboot.repository.UserRepository;
import com.vikasLearning.springboot.service.UserService;
import lombok.AllArgsConstructor;
import com.vikasLearning.springboot.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service    //for building service class.
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }
        //Convert UseDto into User Jpa Entity
        // User user = UserMapper.mapToUser(userDto);

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        //User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);


        //Convert User JPA entity to UserDto.
        //UserDto savedUserDto = UserMapper.mapToUserDto(user);

        //UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("user","id",userId)
        );


        //return  UserMapper.mapToUserDto(user);
        //return  modelMapper.map(user, UserDto.class);

        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        /* return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());*/
//        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());

        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());

    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        User updatedUser = userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);

        //return modelMapper.map(updatedUser, UserDto.class);

        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId)
        );
        userRepository.deleteById(userId);
    }


}
