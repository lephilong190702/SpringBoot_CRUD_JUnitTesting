package com.example.store.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.store.dto.UserDto;
import com.example.store.entity.User;
import com.example.store.repository.RoleRepository;
import com.example.store.repository.UserRepository;
import com.example.store.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void UserService_SaveUser_ReturnUserDto(){
        User user = User.builder()
                    .firstName("Le Phi")
                    .lastName("Long")
                    .email("junit@gmail.com")
                    .password("hashedPassword")
                    .build();
        UserDto userRegistrationDto = UserDto.builder()
                    .firstName("Le Phi")
                    .lastName("Long")
                    .email("junit@gmail.com")
                    .password("1")
                    .build();
        when(passwordEncoder.encode(userRegistrationDto.getPassword())).thenReturn(user.getPassword());
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User savedUser = userService.saveUser(userRegistrationDto);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    public void UserService_GetAllUser_ReturnListUser(){
        List<User> userList = List.of(
            User.builder()
                .firstName("Le")
                .lastName("Phi")
                .email("lephi@gmail.com")
                .password("1")
                .build(),
            User.builder()
                .firstName("Nguyen")
                .lastName("Long")
                .email("longnguyen@gmail.com")
                .password("1")
                .build()
        );

        when(userRepository.findAll()).thenReturn(userList);

        List<UserDto> users = userService.getAllUsers();
        
        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(users.size()).isEqualTo(2);
        Assertions.assertThat(users.get(0).getEmail()).isEqualTo("lephi@gmail.com");
    }

    @Test
    public void UserService_FindByEmail_ReturnUserDto(){
        User user = User.builder()
            .firstName("Le")
            .lastName("Long")
            .email("mockito@gmail.com")
            .password("hashpassword")
            .build();

        when(userRepository.findByEmail("mockito@gmail.com")).thenReturn(user);
        
        UserDto foundUser = userService.findUserByEmail(user.getEmail());

        Assertions.assertThat(foundUser).isNotNull();
    }

    @Test
    public void UserService_UpdateUser_ReturnUserDto(){
        User user = User.builder()
                    .firstName("Le Phi")
                    .lastName("Long")
                    .email("junit@gmail.com")
                    .password("hashedPassword")
                    .build();
        UserDto userDto = UserDto.builder()
                    .firstName("Le Phi")
                    .lastName("Long")
                    .email("junit@gmail.com")
                    .password("1")
                    .build();
        
        when(userRepository.findById((long) 1)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDto updatedUser = userService.updateUser(userDto, (long) 1);
        Assertions.assertThat(updatedUser).isNotNull();
    }

    @Test
    public void UserService_DeleteUserById_ReturnUserDto(){
        User user = User.builder()
                    .firstName("Le Phi")
                    .lastName("Long")
                    .email("junit@gmail.com")
                    .password("hashedPassword")
                    .build();
     
        
        when(userRepository.findById((long) 1)).thenReturn(Optional.ofNullable(user));

        UserDto savedUser = userService.findUserById((long) 1);
        assertAll(() -> userService.deleteUserById((long) 1));
    }
}
