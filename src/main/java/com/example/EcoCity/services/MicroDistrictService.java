package com.example.EcoCity.services;

import com.example.EcoCity.models.dto.request.CreateMicroDistrictRequest;
import com.example.EcoCity.models.dto.request.MicroDistrictRequest;
import com.example.EcoCity.models.dto.response.DistrictResponse;
import com.example.EcoCity.models.dto.response.MicroDistrictResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * @author Tribushko Danil
 * @since 31.03.2024
 * <p>
 * Сервис для работы с микрорайонами
 */
public interface MicroDistrictService {
    /**
     * Создание микрорайона
     *
     * @param email   электронный адрес пользователя
     * @param request запрос на работу с микрорайоном
     * @return dto ответа района
     */
    DistrictResponse create(@Email
                            @Size(min = 7, message = "Email must be contain 7 characters")
                            String email,
                            @Valid
                            CreateMicroDistrictRequest request);

    /**
     * Обновление микрорайона
     *
     * @param email   электронный адрес пользователя
     * @param id      идентификатор района
     * @param request запрос на работу с микрорайоном
     * @return dto запрос на обновление микрорайона
     */
    MicroDistrictResponse update(@Email
                                 @Size(min = 7, message = "Email must be contain 7 characters")
                                 String email,
                                 @Min(value = 1, message = "Id can not be less than 1")
                                 Integer id,
                                 @Valid
                                 MicroDistrictRequest request);

    /**
     * Получение микрорайона с указанным идентификатором
     *
     * @param id идентификатор района
     * @return dto ответа района
     */
    MicroDistrictResponse findById(@Min(value = 1, message = "Id can not be less than 1")
                                   Integer id);

    /**
     * Удаление микрорайона
     *
     * @param email электронный адрес пользователя
     * @param id    идентификатор района
     */
    void delete(@Email
                @Size(min = 7, message = "Email must be contain 7 characters")
                String email,
                @Min(value = 1, message = "Id can not be less than 1")
                Integer id);

    /**
     * Изменение района микрорайона
     *
     * @param email         эдектронный адрес пользователя
     * @param id            идентификатор микрорайона
     * @param newDistrictId идентификатор нового района
     * @return dto ответа района
     */
    DistrictResponse changeDistrict(@Email
                                    @Size(min = 7, message = "Email must be contain 7 characters")
                                    String email,
                                    @Min(value = 1, message = "Id can not be less than 1")
                                    Integer id,
                                    @Min(value = 1, message = "Id can not be less than 1")
                                    Integer newDistrictId);
}