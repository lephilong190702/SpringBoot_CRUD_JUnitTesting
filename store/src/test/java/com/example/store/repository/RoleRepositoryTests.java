package com.example.store.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.store.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void RoleRepository_SaveRole_ReturnRoleNotNull(){
        Role role = Role.builder()
                        .name("ROLE_TEST")
                        .build();
        roleRepository.save(role);

        
        Assertions.assertThat(role.getId()).isNotNull();
        Assertions.assertThat(role.getId()).isGreaterThan(0);
    }

    @Test
    public void RoleRepository_GetAllRole_ReturnRoleMoreThanOne(){
        Role role = Role.builder()
                        .name("ROLE_TEST")
                        .build();
        Role role1 = Role.builder()
                        .name("ROLE_TEST1")
                        .build();

        roleRepository.save(role);
        roleRepository.save(role1);

        List<Role> roleList = roleRepository.findAll();
        
        Assertions.assertThat(roleList).isNotNull();
        Assertions.assertThat(roleList.size()).isEqualTo(2);
    }

    @Test
    public void RoleRepository_FindByName_ReturnRoleNotNull(){
        Role role = Role.builder()
                        .name("ROLE_TEST")
                        .build();
        roleRepository.save(role);

        Role r = roleRepository.findByName(role.getName());
        Assertions.assertThat(r).isNotNull();
    }

    @Test
    public void RoleRepository_UpdateRole_ReturnRoleNotNull(){
        Role role = Role.builder()
                        .name("ROLE_TEST")
                        .build();
        roleRepository.save(role);

        Role roleSave = roleRepository.findById(role.getId()).get();
        roleSave.setName("ROLE_ADMIN");
        
        Role updatedRole = roleRepository.save(roleSave);

        Assertions.assertThat(updatedRole).isNotNull();
    }

    @Test
    public void RoleRepository_DeleteRole_ReturnRoleNotNull(){
        Role role = Role.builder()
                        .name("ROLE_TEST")
                        .build();
        roleRepository.save(role);

        roleRepository.deleteById(role.getId());
        Optional<Role> roleList = roleRepository.findById(role.getId());
        Assertions.assertThat(roleList).isEmpty();
    }
}
