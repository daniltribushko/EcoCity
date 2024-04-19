package com.example.EcoCity.services.imp;

import com.example.EcoCity.models.dto.request.DistrictRequest;
import com.example.EcoCity.models.dto.response.DistrictResponse;
import com.example.EcoCity.models.entities.District;
import com.example.EcoCity.services.db.DBServiceDistrict;
import com.example.EcoCity.services.iml.DistrictServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

/**
 * @author Tribushko Danil
 * @since 18.04.2024
 * <p>
 * Класс для тестирование сервиса по работе с районами
 */
@ExtendWith(MockitoExtension.class)
class DistrictServiceTest {
    @Mock
    private DBServiceDistrict dbServiceDistrict;
    @InjectMocks
    private DistrictServiceImp districtService;

    @Test
    @Order(1)
    void createDistrictTest() {
        DistrictRequest request = new DistrictRequest("District 1");
        DistrictResponse actual = districtService.create(null, request);

        Assertions.assertEquals("District 1", actual.getName());
    }

    @Test
    @Order(2)
    void updateDistrictTest() {
        DistrictRequest request = new DistrictRequest("Updated district");

        District district = new District("District 1");
        district.setId(1);
        district.setMicroDistricts(new HashSet<>());
        Mockito.when(dbServiceDistrict.findById(1)).thenReturn(district);
        DistrictResponse actual = districtService.update(null, 1, request);

        Assertions.assertEquals(1, actual.getId());
        Assertions.assertEquals("Updated district", actual.getName());
    }

    @Test
    @Order(3)
    void findDistrictById() {
        District district = new District("District 1");
        district.setId(1);
        district.setMicroDistricts(new HashSet<>());

        Mockito.when(dbServiceDistrict.findById(1)).thenReturn(district);

        DistrictResponse actual = districtService.update(null, 1,
                new DistrictRequest("Updated district"));

        Assertions.assertEquals(1, actual.getId());
        Assertions.assertEquals("Updated district", actual.getName());
    }
}
