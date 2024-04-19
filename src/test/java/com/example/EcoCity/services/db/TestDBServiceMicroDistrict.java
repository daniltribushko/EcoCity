package com.example.EcoCity.services.db;

import com.example.EcoCity.exceptions.microdistricts.MicroDistrictAlreadyExistException;
import com.example.EcoCity.exceptions.microdistricts.MicroDistrictByIdNotFoundException;
import com.example.EcoCity.models.entities.MicroDistrict;
import com.example.EcoCity.repositories.MicroDistrictRepository;
import com.example.EcoCity.services.db.imp.DBServiceMicroDistrictImp;
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
 * @since 17.04.2024
 * <p>
 * Класс для тестирования сервиса по работе с микрорайонами в бд
 */
@ExtendWith(MockitoExtension.class)
class TestDBServiceMicroDistrict {
    @Mock
    private MicroDistrictRepository microDistrictRepository;

    @InjectMocks
    private DBServiceMicroDistrictImp dbServiceMicroDistrict;

    private List<MicroDistrict> testData;

    @BeforeEach
    void addTestData(){
        testData = new ArrayList<>();

        MicroDistrict microDistrict1 = new MicroDistrict("Micro district 1", null);
        MicroDistrict microDistrict2 = new MicroDistrict("Micro district 2", null);
        MicroDistrict microDistrict3 = new MicroDistrict("Micro district 3", null);

        testData.add(microDistrict1);
        testData.add(microDistrict2);
        testData.add(microDistrict3);
    }

    @Test
    @Order(1)
    void createMicroDistrictAlreadyExistExceptionTest(){
        Class<MicroDistrictAlreadyExistException> exceptionClass = MicroDistrictAlreadyExistException.class;
        Mockito.when(microDistrictRepository.existsByName("test")).thenThrow(exceptionClass);

        MicroDistrictAlreadyExistException exception = Assertions.assertThrows(exceptionClass,
                () -> dbServiceMicroDistrict.save(new MicroDistrict("test", null)));

        Assertions.assertEquals(exceptionClass, exception.getClass());
    }

    @Test
    @Order(2)
    void findMicroDistrictByIdNotFoundExceptionTest(){
        Class<MicroDistrictByIdNotFoundException> exceptionClass = MicroDistrictByIdNotFoundException.class;
        Mockito.when(microDistrictRepository.findById(4)).thenThrow(exceptionClass);

        MicroDistrictByIdNotFoundException exception = Assertions.assertThrows(exceptionClass,
                () -> dbServiceMicroDistrict.findById(4));

        Assertions.assertEquals(exceptionClass, exception.getClass());
    }

    @Test
    @Order(3)
    void findMicroDistrictByIdTet(){
        Mockito.when(microDistrictRepository.findById(1)).thenReturn(Optional.of(testData.get(0)));
        Mockito.when(microDistrictRepository.findById(3)).thenReturn(Optional.of(testData.get(2)));

        MicroDistrict microDistrict1 = dbServiceMicroDistrict.findById(1);
        MicroDistrict microDistrict3 = dbServiceMicroDistrict.findById(3);

        Assertions.assertEquals("Micro district 1", microDistrict1.getName());
        Assertions.assertEquals("Micro district 3", microDistrict3.getName());
    }
}
