package com.example.store.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.store.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_SaveUser_ReturnUserNotNull(){
        User user = User.builder()
                    .firstName("Le Phi")
                    .lastName("Long")
                    .email("junit@gmail.com")
                    .build();

        userRepository.save(user);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_GetAllUser_ReturnUserNotNull(){
        User user = User.builder()
                    .firstName("Le Phi")
                    .lastName("Long")
                    .email("junit@gmail.com")
                    .build();
        User user1 = User.builder()
                    .firstName("Le Phi")
                    .lastName("Long1")
                    .email("junit1@gmail.com")
                    .build();

        userRepository.save(user);
        userRepository.save(user1);

        List<User> userList = userRepository.findAll();

        Assertions.assertThat(userList).isNotNull();
        Assertions.assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    public void UserRepository_FindByEmail_ReturnUserNotNull(){
        User user = User.builder()
                    .firstName("Le Phi")
                    .lastName("Long")
                    .email("junit@gmail.com")
                    .build();

        userRepository.save(user);

        User u = userRepository.findByEmail(user.getEmail());

        Assertions.assertThat(u).isNotNull();
    }

    @Test
    public void UserRepository_UpdateUser_ReturnUserNotNull(){
        User user = User.builder()
                    .firstName("Le Phi")
                    .lastName("Long")
                    .email("junit@gmail.com")
                    .build();

        userRepository.save(user);

        User userSave = userRepository.findById(user.getId()).get();
        userSave.setFirstName("Long");
        userSave.setLastName("Le Phi");
        userSave.setEmail("test@gmail.com");
        user.setPassword("123456");

        User updatedUser = userRepository.save(userSave);

        Assertions.assertThat(updatedUser.getFirstName()).isNotNull();
        Assertions.assertThat(updatedUser.getLastName()).isNotNull();
        Assertions.assertThat(updatedUser.getEmail()).isNotNull();
        Assertions.assertThat(updatedUser.getPassword()).isNotNull();
    }

    @Test
    public void UserRepository_DeleteUser_ReturnUserIsEmpty(){
        User user = User.builder()
                    .firstName("Le Phi")
                    .lastName("Long")
                    .email("junit@gmail.com")
                    .build();

        userRepository.save(user);


        userRepository.deleteById(user.getId());
        Optional<User> userReturn = userRepository.findById(user.getId());

        Assertions.assertThat(userReturn).isEmpty();
    }

}
