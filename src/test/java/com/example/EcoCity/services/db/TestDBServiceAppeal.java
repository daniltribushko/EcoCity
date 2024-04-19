package com.example.EcoCity.services.db;

import com.example.EcoCity.exceptions.appeals.AppealByIdNotFoundException;
import com.example.EcoCity.models.entities.Appeal;
import com.example.EcoCity.models.enums.AppealStatus;
import com.example.EcoCity.repositories.AppealRepository;
import com.example.EcoCity.services.db.imp.DBServiceAppealImp;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 16.04.2024
 * <p>
 * Класс для тестирования сервиса по работе с сервисами по работе с обращениями в бд
 */
@ExtendWith(MockitoExtension.class)
class TestDBServiceAppeal {
    @Mock
    private AppealRepository appealRepository;
    @InjectMocks
    private DBServiceAppealImp dbServiceAppeal;

    private List<Appeal> testData;

    @BeforeEach
    void addData() {
        testData = new ArrayList<>();
        Appeal appeal1 = new Appeal()
                .builder()
                .id(1L)
                .text("Test text appeal 1")
                .address("Test address appeal 1")
                .status(AppealStatus.WAITING)
                .build();
        Appeal appeal2 = new Appeal()
                .builder()
                .id(2L)
                .text("Test text appeal 2")
                .address("Test address appeal 2")
                .status(AppealStatus.WAITING)
                .build();
        Appeal appeal3 = new Appeal()
                .builder()
                .id(3L)
                .text("Test text appeal 3")
                .address("Test address appeal 3")
                .status(AppealStatus.WAITING)
                .build();

        testData.add(appeal1);
        testData.add(appeal2);
        testData.add(appeal3);
    }

    @Test
    @Order(1)
    void findAppealByIdTest() {
        Mockito.when(appealRepository.findById(1L)).thenReturn(Optional.of(testData.get(0)));
        Mockito.when(appealRepository.findById(2L)).thenReturn(Optional.of(testData.get(1)));
        Mockito.when(appealRepository.findById(3L)).thenReturn(Optional.of(testData.get(2)));

        Assertions.assertEquals(1L, dbServiceAppeal.findById(1L).getId());
        Assertions.assertEquals(2L, dbServiceAppeal.findById(2L).getId());
        Assertions.assertEquals(3L, dbServiceAppeal.findById(3L).getId());
    }

    @Test
    @Order(2)
    void findAppealByIdThrowExceptionTest() {
        AppealByIdNotFoundException exception = Assertions.assertThrows(AppealByIdNotFoundException.class,
                () -> dbServiceAppeal.findById(4L));
        Assertions.assertEquals("Appeal with 4 id not found exception", exception.getMessage());
    }

}
