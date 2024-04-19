package com.example.EcoCity.services.imp;

import com.example.EcoCity.models.dto.request.AppealTypeRequest;
import com.example.EcoCity.models.dto.response.AppealTypeResponse;
import com.example.EcoCity.models.dto.response.AppealsResponse;
import com.example.EcoCity.models.entities.*;
import com.example.EcoCity.services.db.DBServiceAppealType;
import com.example.EcoCity.services.iml.AppealTypeServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 18.04.2024
 * <p>
 * Класс для тестирования сервиса по работе с типами обращения
 */
@ExtendWith(MockitoExtension.class)
class AppealTypeTest {
    @Mock
    private DBServiceAppealType dbServiceAppealType;
    @InjectMocks
    private AppealTypeServiceImp appealTypeService;

    @Test
    @Order(1)
    void createAppealTypeTest() {
        AppealTypeResponse expected = new AppealTypeResponse(1, "Appeal type");
        AppealTypeResponse actual = appealTypeService.create("test", new AppealTypeRequest("Appeal type"));

        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    @Test
    @Order(2)
    void updateAppealTypeTest() {
        AppealType appealType = new AppealType("Appeal type");
        appealType.setId(1);

        Mockito.when(dbServiceAppealType.findById(1)).thenReturn(appealType);

        AppealTypeResponse actual = appealTypeService.update("test", 1,
                new AppealTypeRequest("Updated type"));
        AppealTypeResponse expected = new AppealTypeResponse(1, "Updated type");

        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    @Test
    @Order(3)
    void findAppealTypeById() {
        AppealType appealType = new AppealType("Appeal type");
        appealType.setId(1);

        Mockito.when(dbServiceAppealType.findById(1)).thenReturn(appealType);

        AppealTypeResponse expected = new AppealTypeResponse(1, "Appeal type");
        AppealTypeResponse actual = appealTypeService.findById(1);

        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAppealsFromAppealType() {
        AppealType appealType = new AppealType("Appeal type");
        appealType.setId(1);

        appealType.setAppeals(Set.of(new Appeal()
                        .builder()
                        .id(1L)
                        .type(appealType)
                        .district(new District())
                        .microDistrict(new MicroDistrict())
                        .author(new User())
                        .build(),
                new Appeal()
                        .builder()
                        .id(2L)
                        .type(appealType)
                        .district(new District())
                        .microDistrict(new MicroDistrict())
                        .author(new User())
                        .build(),
                new Appeal()
                        .builder()
                        .id(3L)
                        .type(appealType)
                        .district(new District())
                        .microDistrict(new MicroDistrict())
                        .author(new User())
                        .build()));

        Mockito.when(dbServiceAppealType.findById(1)).thenReturn(appealType);

        AppealsResponse actual = appealTypeService.getAppeals(1);

        Assertions.assertEquals(3, actual.getAppeals().size());
    }
}
