package com.example.EcoCity.services.imp;

import com.example.EcoCity.models.dto.request.AppealRequest;
import com.example.EcoCity.models.dto.request.CreateAppealRequest;
import com.example.EcoCity.models.dto.response.*;
import com.example.EcoCity.models.entities.*;
import com.example.EcoCity.models.enums.AppealStatus;
import com.example.EcoCity.services.db.*;
import com.example.EcoCity.services.iml.AppealServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Tribushko Danil
 * @since 18.04.2024
 * <p>
 * Класс для тестирование сервиса по работе с обращениями
 */
@ExtendWith(MockitoExtension.class)
class AppealServiceTest {
    @Mock
    private DBServiceAppeal dbServiceAppeal;
    @Mock
    private DBServiceUser dbServiceUser;
    @Mock
    private DBServiceDistrict dbServiceDistrict;
    @Mock
    private DBServiceMicroDistrict dbServiceMicroDistrict;
    @Mock
    private DBServiceAppealType dbServiceAppealType;

    @InjectMocks
    private AppealServiceImp appealService;

    @Test
    @Order(1)
    void createAppealTest() {
        CreateAppealRequest request = new CreateAppealRequest("Appeal text",
                "Appeal address",
                1,
                1,
                1);
        AppealType appealType = new AppealType("Appeal type 1");
        District district = new District("District 1");
        MicroDistrict microDistrict = new MicroDistrict("Micro district 1", null);

        Mockito.when(dbServiceAppealType.findById(1)).thenReturn(appealType);
        Mockito.when(dbServiceDistrict.findById(1)).thenReturn(district);
        Mockito.when(dbServiceMicroDistrict.findById(1)).thenReturn(microDistrict);
        Mockito.when(dbServiceUser.findByEmail("test")).thenReturn(new User().builder().id(1L).email("test").build());

        Assertions.assertEquals("Appeal address",
                appealService.create("test", request).getAddress());
    }

    @Test
    @Order(2)
    void updateAppealTest() {
        AppealRequest request = new AppealRequest("Updated text", null);
        User user = new User()
                .builder()
                .email("Test user")
                .build();
        Appeal appeal = new Appeal()
                .builder()
                .id(1L)
                .text("Appeal text")
                .type(new AppealType("Appeal type"))
                .district(new District("District"))
                .microDistrict(new MicroDistrict("Micro district", null))
                .author(user)
                .address("Appeal address")
                .build();

        Mockito.when(dbServiceAppeal.findById(1L)).thenReturn(appeal);

        AppealResponse appealResponse = appealService.update("Test user", 1L, request);
        Assertions.assertEquals("Updated text", appealResponse.getText());

    }

    @Test
    @Order(3)
    void findAppealByIdTest() {
        Appeal appeal1 = new Appeal()
                .builder()
                .id(1L)
                .text("Appeal text 1")
                .type(new AppealType("Appeal type 1"))
                .district(new District("District 1"))
                .microDistrict(new MicroDistrict("Micro district 1", null))
                .author(new User().builder().build())
                .status(AppealStatus.WAITING)
                .build();
        Mockito.when(dbServiceAppeal.findById(1L)).thenReturn(appeal1);

        AppealResponse response = appealService.findById(1L);
        AppealResponse expected = new AppealResponse()
                .builder()
                .id(1L)
                .text("Appeal text 1")
                .type(new AppealTypeResponse(1, "Appeal type 1"))
                .district(new DistrictResponse(1, "District 1"))
                .microDistrict(new MicroDistrictResponse(1, "Micro district 1"))
                .author(new UserResponse().builder().build())
                .address("Appeal address 1")
                .status(AppealStatus.WAITING)
                .build();

        Assertions.assertEquals(expected.getId(), response.getId());
        Assertions.assertEquals(expected.getText(), response.getText());
        Assertions.assertEquals(expected.getStatus(), response.getStatus());
    }
}
