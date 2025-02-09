package com.example.store.service;

import java.util.List;

import com.example.store.dto.UserDto;
import com.example.store.entity.User;

public interface UserService {
    User saveUser(UserDto userRegistrationDto);
    UserDto findUserByEmail(String email);
    UserDto findUserById(Long id);
    UserDto updateUser(UserDto userDto, Long id);
    void deleteUserById(Long id);
    List<UserDto> getAllUsers();
} 
