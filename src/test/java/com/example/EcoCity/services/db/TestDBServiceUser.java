package com.example.EcoCity.services.db;

import com.example.EcoCity.exceptions.users.UserAlreadyExistException;
import com.example.EcoCity.exceptions.users.UserByEmailNotFoundException;
import com.example.EcoCity.exceptions.users.UserByIdNotFoundException;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.repositories.UserRepository;
import com.example.EcoCity.services.db.imp.DBServiceUserImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 17.04.2024
 * <p>
 * Класс для тестирования сервиса по работе с пользователями в бд
 */
@ExtendWith(MockitoExtension.class)
class TestDBServiceUser {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private DBServiceUserImp dbServiceUser;

    private List<User> testData;

    @BeforeEach
    void addData(){
        testData = new ArrayList<>();

        User user1 = new User()
                .builder()
                .id(1L)
                .email("1@bk.ru")
                .surname("Ivanov")
                .name("Ivan")
                .createDate(LocalDateTime.now())
                .lastOnlineDate(LocalDateTime.of(2024, 1, 1, 1, 1, 1))
                .password("1234")
                .build();

        User user2 = new User()
                .builder()
                .id(2L)
                .email("2@bk.ru")
                .surname("Petrov")
                .name("Petr")
                .createDate(LocalDateTime.now())
                .lastOnlineDate(LocalDateTime.of(2024, 2, 2, 2, 2, 2))
                .password("1234")
                .build();

        User user3 = new User()
                .builder()
                .id(3L)
                .email("3@bk.ru")
                .surname("Aleksandrov")
                .name("Aleksandr")
                .createDate(LocalDateTime.now())
                .lastOnlineDate(LocalDateTime.of(2024, 3, 3, 3, 3, 3))
                .password("1234")
                .build();

        testData.add(user1);
        testData.add(user2);
        testData.add(user3);
    }

    @Test
    @Order(1)
    void saveUserAlreadyExistExceptionTest(){
        Class<UserAlreadyExistException> exceptionClass = UserAlreadyExistException.class;
        Mockito.when(userRepository.existsByEmail("test")).thenThrow(exceptionClass);
        UserAlreadyExistException exception = Assertions.assertThrows(exceptionClass,
                () -> dbServiceUser.save(new User()
                        .builder()
                        .email("test")
                        .build()));

        Assertions.assertEquals(exceptionClass, exception.getClass());
    }

    @Test
    @Order(2)
    void findUserByIdNotFoundExceptionTest(){
        Class<UserByIdNotFoundException> exceptionClass = UserByIdNotFoundException.class;
        Mockito.when(userRepository.findById(4L)).thenThrow(exceptionClass);
        UserByIdNotFoundException exception = Assertions.assertThrows(exceptionClass,
                () -> dbServiceUser.findById(4L));

        Assertions.assertEquals(exceptionClass, exception.getClass());
    }

    @Test
    @Order(3)
    void findUserByEmailNotFoundExceptionTest(){
        Class<UserByEmailNotFoundException> exceptionClass = UserByEmailNotFoundException.class;
        Mockito.when(userRepository.findByEmail("test")).thenThrow(exceptionClass);
        UserByEmailNotFoundException exception = Assertions.assertThrows(exceptionClass,
                () -> dbServiceUser.findByEmail("test"));

        Assertions.assertEquals(exceptionClass, exception.getClass());
    }

    @Test
    @Order(4)
    void findUserByIdTest(){
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(testData.get(0)));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(testData.get(1)));

        User user1 = dbServiceUser.findById(1L);
        User user2 = dbServiceUser.findById(2L);

        Assertions.assertEquals(1L, user1.getId());
        Assertions.assertEquals(2L, user2.getId());
    }
}
