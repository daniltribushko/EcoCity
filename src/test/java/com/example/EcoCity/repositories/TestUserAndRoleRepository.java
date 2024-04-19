package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.models.enums.RecordState;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 15.04.2024
 * <p>
 * Класс для тестирования репозитория по работе с пользователями
 */
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestUserAndRoleRepository {
    private final UserRepository userRepository;

    @Autowired
    TestUserAndRoleRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeAll
    void addUsers() {
        User user = new User()
                .builder()
                .id(1L)
                .email("1@gmail.com")
                .surname("Ivanov")
                .name("Ivan")
                .recordState(RecordState.ACTIVE)
                .roles(null)
                .password("123")
                .createDate(LocalDateTime.now())
                .lastOnlineDate(LocalDateTime.now())
                .build();
        userRepository.save(user);
    }

    @Test
    @Order(1)
    void saveUserTest() {
        Assertions.assertEquals(1, userRepository.findAll().size());
    }

    @Test
    @Order(2)
    void findUserByIdTest() {
        User user = userRepository.findById(1L).orElse(null);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    @Order(3)
    void updateUserTest() {
        User user = userRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(user);

        user.setRecordState(RecordState.DELETED);
        userRepository.save(user);
        user = userRepository.findById(1L).orElse(null);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(RecordState.DELETED, user.getRecordState());
    }

    @Test
    @Order(4)
    void findByUserEmailAndExistByEmailTest() {
        String testEmail = "1@gmail.com";
        User user = userRepository.findByEmail(testEmail).orElse(null);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(testEmail, user.getUsername());
        Assertions.assertTrue(userRepository.existsByEmail(testEmail));
    }

    @Test
    @Order(5)
    void deleteUserTest() {
        User user = userRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(user);

        userRepository.delete(user);

        Assertions.assertEquals(0, userRepository.findAll().size());
    }
}
