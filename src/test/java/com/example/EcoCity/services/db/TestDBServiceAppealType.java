package com.example.EcoCity.services.db;

import com.example.EcoCity.exceptions.appeals.AppealTypeAlreadyExistException;
import com.example.EcoCity.exceptions.appeals.AppealTypeByIdNotFoundException;
import com.example.EcoCity.models.entities.AppealType;
import com.example.EcoCity.repositories.AppealTypeRepository;
import com.example.EcoCity.services.db.imp.DBServiceAppealTypeImp;
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
 * Класс для тестиования сервиса по работе с типами обращений в бд
 */
@ExtendWith(MockitoExtension.class)
class TestDBServiceAppealType {
    @Mock
    private AppealTypeRepository appealTypeRepository;

    @InjectMocks
    private DBServiceAppealTypeImp dbServiceAppealType;

    private List<AppealType> testData;

    @BeforeEach
    void addData(){
        testData = new ArrayList<>();

        AppealType appealType1 = new AppealType("Appeal type 1");
        AppealType appealType2 = new AppealType("Appeal type 2");
        AppealType appealType3 = new AppealType("Appeal type 3");

        testData.add(appealType1);
        testData.add(appealType2);
        testData.add(appealType3);
    }

    @Test
    @Order(1)
    void createAppealTypeAlreadyExistExceptionTest(){
        Class<AppealTypeAlreadyExistException> exceptionClass = AppealTypeAlreadyExistException.class;
        Mockito.when(appealTypeRepository.existsByName("Test type"))
                .thenThrow(exceptionClass);
        AppealTypeAlreadyExistException exception = Assertions.assertThrows(exceptionClass,
                () -> dbServiceAppealType.save(new AppealType("Test type")));
        Assertions.assertEquals(exceptionClass, exception.getClass());
    }

    @Test
    @Order(2)
    void findAppealTypeByIdNotFoundException(){
        Class<AppealTypeByIdNotFoundException> exceptionClass = AppealTypeByIdNotFoundException.class;
        Mockito.when(appealTypeRepository.findById(4))
                .thenThrow(exceptionClass);
        AppealTypeByIdNotFoundException exception = Assertions.assertThrows(exceptionClass,
                () -> dbServiceAppealType.findById(4));
        Assertions.assertEquals(exceptionClass, exception.getClass());
    }

    @Test
    @Order(3)
    void findAppealTypeByIdTest(){
        Mockito.when(appealTypeRepository.findById(1)).thenReturn(Optional.of(testData.get(0)));
        Mockito.when(appealTypeRepository.findById(3)).thenReturn(Optional.of(testData.get(2)));

        Assertions.assertEquals("Appeal type 1", dbServiceAppealType.findById(1).getName());
        Assertions.assertEquals("Appeal type 3", dbServiceAppealType.findById(3).getName());
    }
}
