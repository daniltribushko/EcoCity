package com.example.EcoCity.services.imp;

import com.example.EcoCity.models.dto.request.CreateMicroDistrictRequest;
import com.example.EcoCity.models.dto.request.MicroDistrictRequest;
import com.example.EcoCity.models.dto.response.DistrictResponse;
import com.example.EcoCity.models.dto.response.MicroDistrictResponse;
import com.example.EcoCity.models.entities.District;
import com.example.EcoCity.models.entities.MicroDistrict;
import com.example.EcoCity.services.db.DBServiceDistrict;
import com.example.EcoCity.services.db.DBServiceMicroDistrict;
import com.example.EcoCity.services.iml.MicroDistrictServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 19.04.2024
 * <p>
 * Класс для тестирования сервиса по работе с микро районами
 */
@ExtendWith(MockitoExtension.class)
class MicroDistrictServiceTest {
    @Mock
    private DBServiceDistrict dbServiceDistrict;
    @Mock
    private DBServiceMicroDistrict dbServiceMicroDistrict;
    @InjectMocks
    private MicroDistrictServiceImp microDistrictService;

    @Test
    @Order(1)
    void createMicroDistrictTest() {
        CreateMicroDistrictRequest request = new CreateMicroDistrictRequest(1, "Micro district 1");

        District district = new District("District 1");
        district.setId(1);
        district.setMicroDistricts(new HashSet<>());

        Mockito.when(dbServiceDistrict.findById(1)).thenReturn(district);

        DistrictResponse expected = microDistrictService.create(null, request);

        Assertions.assertEquals(1, expected.getId());
        Assertions.assertEquals(1, expected.getMicroDistricts().size());
    }

    @Test
    @Order(2)
    void updateMicroDistrictTest() {
        MicroDistrictRequest request = new MicroDistrictRequest("Updated micro district");

        MicroDistrict microDistrict = new MicroDistrict("Micro district 1", null);
        microDistrict.setId(1);

        Mockito.when(dbServiceMicroDistrict.findById(1)).thenReturn(microDistrict);

        MicroDistrictResponse actual = microDistrictService.update(null, 1, request);

        Assertions.assertEquals(1, actual.getId());
        Assertions.assertEquals("Updated micro district", actual.getName());
    }

    @Test
    @Order(3)
    void findMicroDistrictByIdTest(){
        MicroDistrict microDistrict = new MicroDistrict("Micro district 1", null);
        microDistrict.setId(1);

        Mockito.when(dbServiceMicroDistrict.findById(1)).thenReturn(microDistrict);
        MicroDistrictResponse actual = microDistrictService.findById(1);

        Assertions.assertEquals(1, actual.getId());
        Assertions.assertEquals("Micro district 1", actual.getName());
    }

    @Test
    @Order(4)
    void changeDistrictIdTest(){
        District oldDistrict = new District("District 1");
        MicroDistrict microDistrict = new MicroDistrict("Micro district", oldDistrict);
        microDistrict.setId(1);
        oldDistrict.setId(1);
        Set<MicroDistrict> microDistricts = new HashSet<>();
        microDistricts.add(microDistrict);
        oldDistrict.setMicroDistricts(microDistricts);

        District newDistrict = new District("District 2");
        newDistrict.setId(2);
        newDistrict.setMicroDistricts(new HashSet<>());

        Mockito.when(dbServiceMicroDistrict.findById(1)).thenReturn(microDistrict);
        Mockito.when(dbServiceDistrict.findById(2)).thenReturn(newDistrict);
        DistrictResponse actual = microDistrictService.changeDistrict(null, 1, 2);

        Assertions.assertEquals(2, actual.getId());
        Assertions.assertEquals("District 2", actual.getName());
        Assertions.assertEquals(1, actual.getMicroDistricts().size());
    }
}
