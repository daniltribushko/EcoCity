package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 16.04.2024
 * <p>
 * Класс для тестирования репозитория для работы с ролями
 */
@DataJpaTest
class TestRoleRepository {
    private final RoleRepository roleRepository;

    @Autowired
    TestRoleRepository(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @BeforeEach
    void addRoles(){
        Role role1 = new Role("Role 1");
        Role role2 = new Role("Role 2");
        Role role3 = new Role("Role 3");

        roleRepository.saveAll(List.of(role1, role2, role3));
    }

    @Test
    void findRoleByName(){
        Role role2 = roleRepository.findById(2).orElse(null);

        Assertions.assertNotNull(role2);
        Assertions.assertEquals("Role 2", role2.getName());
    }
}