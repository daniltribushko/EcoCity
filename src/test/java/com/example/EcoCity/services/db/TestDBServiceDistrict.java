package com.example.EcoCity.services.db;

import com.example.EcoCity.exceptions.districts.DistrictAlreadyExistException;
import com.example.EcoCity.exceptions.districts.DistrictByIdNotFoundException;
import com.example.EcoCity.models.entities.District;
import com.example.EcoCity.repositories.DistrictRepository;
import com.example.EcoCity.services.db.imp.DBServiceDistrictImp;
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
 * Класс для тестирования сервиса по работе с районами в бд
 */
@ExtendWith(MockitoExtension.class)
class TestDBServiceDistrict {
    @Mock
    private DistrictRepository districtRepository;

    @InjectMocks
    private DBServiceDistrictImp dbServiceDistrict;

    private List<District> testData;

    @BeforeEach
    void addData() {
        testData = new ArrayList<>();

        District district1 = new District("District 1");
        District district2 = new District("District 2");
        District district3 = new District("District 3");

        testData.add(district1);
        testData.add(district2);
        testData.add(district3);
    }

    @Test
    @Order(1)
    void createDistrictAlreadyExistExceptionTest() {
        Class<DistrictAlreadyExistException> exceptionClass = DistrictAlreadyExistException.class;
        Mockito.when(districtRepository.existsByName("test")).thenThrow(exceptionClass);

        DistrictAlreadyExistException exception = Assertions.assertThrows(exceptionClass,
                () -> dbServiceDistrict.save(new District("test")));
        Assertions.assertEquals(exceptionClass, exception.getClass());
    }

    @Test
    @Order(2)
    void findDistrictByIdNotFoundExceptionTest() {
        Class<DistrictByIdNotFoundException> exceptionClass = DistrictByIdNotFoundException.class;
        Mockito.when(districtRepository.findById(4)).thenThrow(exceptionClass);

        DistrictByIdNotFoundException exception = Assertions.assertThrows(exceptionClass,
                () -> dbServiceDistrict.findById(4));
        Assertions.assertEquals(exceptionClass, exception.getClass());
    }

    @Test
    @Order(3)
    void findDistrictByIdTest() {
        Mockito.when(districtRepository.findById(1)).thenReturn(Optional.of(testData.get(0)));
        Mockito.when(districtRepository.findById(2)).thenReturn(Optional.of(testData.get(1)));

        District district1 = dbServiceDistrict.findById(1);
        District district2 = dbServiceDistrict.findById(2);

        Assertions.assertEquals("District 1", district1.getName());
        Assertions.assertEquals("District 2", district2.getName());
    }
}
