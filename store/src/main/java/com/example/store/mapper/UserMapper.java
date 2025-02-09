package com.example.store.mapper;

import com.example.store.dto.UserDto;
import com.example.store.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        return new UserDto(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPassword(),
            user.getRoles()
        );
    }

    public static User mapToUser(UserDto userRegistrationDto){
        return new User(
            userRegistrationDto.getFirstName(),
            userRegistrationDto.getLastName(),
            userRegistrationDto.getEmail(),
            userRegistrationDto.getPassword(),
            userRegistrationDto.getRoles()
        );
    }
}
