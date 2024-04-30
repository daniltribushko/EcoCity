package com.example.EcoCity.services.db;

import com.example.EcoCity.exceptions.users.UserAlreadyExistException;
import com.example.EcoCity.exceptions.users.UserByEmailNotFoundException;
import com.example.EcoCity.exceptions.users.UserByIdNotFoundException;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.models.enums.RecordState;
import com.example.EcoCity.repositories.UserPaginationRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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
    @Mock
    private UserPaginationRepository userPaginationRepository;
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
                .recordState(RecordState.ACTIVE)
                .createDate(LocalDateTime.now())
                .lastOnlineDate(LocalDateTime.of(2024, 3, 3, 3, 3, 3))
                .password("1234")
                .build();

        User user4 = new User()
                .builder()
                .id(4L)
                .recordState(RecordState.ACTIVE)
                .email("4@bk.ru")
                .surname("Aleksandrov")
                .name("Aleksandr")
                .createDate(LocalDateTime.now())
                .lastOnlineDate(LocalDateTime.of(2024, 4, 4, 4, 4, 4))
                .password("1234")
                .build();

        User user5 = new User()
                .builder()
                .id(5L)
                .email("5@bk.ru")
                .surname("Aleksandrov")
                .name("Aleksandr")
                .createDate(LocalDateTime.now())
                .lastOnlineDate(LocalDateTime.of(2024, 5, 5, 5, 5, 5))
                .password("1234")
                .build();

        testData.add(user1);
        testData.add(user2);
        testData.add(user3);
        testData.add(user4);
        testData.add(user5);
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

    @Test
    @Order(5)
    void findAllUsers(){
        Mockito.when(userRepository.findAll()).thenReturn(testData);

        Assertions.assertEquals(5, dbServiceUser.findAll().size());
    }

    @Test
    @Order(6)
    void findAllUsersWitPagination(){
        Page<User> page1 = new PageImpl<>(List.of(testData.get(2), testData.get(3)));
        Page<User> page2 = new PageImpl<>(List.of(testData.get(3), testData.get(4)));

        Mockito.when(userPaginationRepository.findAll(PageRequest.of(1, 2)))
                .thenReturn(page1);
        Mockito.when(userPaginationRepository.findAll(PageRequest.of(1, 3)))
                .thenReturn(page2);

        List<User> users1 = dbServiceUser.findAllWithPagination(1,
                2,
                RecordState.ACTIVE,
                null,
                null,
                null);

        List<User> users2 = dbServiceUser.findAllWithPagination(1,
                3,
                null,
                null,
                LocalDateTime.of(2024, 4, 4, 4, 4, 4),
                null);
        Assertions.assertEquals(2, users1.size());
        Assertions.assertEquals(1, users2.size());
        Assertions.assertEquals(4, users2.get(0).getId());
    }
}
