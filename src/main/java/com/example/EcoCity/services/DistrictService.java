package com.example.EcoCity.services;

import com.example.EcoCity.models.dto.request.DistrictRequest;
import com.example.EcoCity.models.dto.response.DistrictResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * @author Tribushko Danil
 * @since 31.03.2024
 * <p>
 * Сервис по работе с районами
 */
public interface DistrictService {
    /**
     * Создание нового района
     *
     * @param request запрос на создание района
     * @return dto ответа района
     */
    DistrictResponse create(@Email
                            @Size(min = 7, message = "Email must be contain 7 characters")
                            String email,
                            @Valid
                            DistrictRequest request);

    /**
     * Поиск района с указанным идентификатором
     *
     * @param id идентификатор района
     * @return dto ответа района
     */
    DistrictResponse findById(@Min(value = 1, message = "Id can not be less than 1")
                              Integer id);

    /**
     * Удаление района
     *
     * @param id идентификатор района
     */
    void delete(@Email
                @Size(min = 7, message = "Email must be contain 7 characters")
                String email,
                @Min(value = 1, message = "Id can not be less than 1")
                Integer id);

    /**
     * Обновление района
     *
     * @param request запрос на обновление района
     * @return dto ответа района
     */
    DistrictResponse update(@Email
                            @Size(min = 7, message = "Email must be contain 7 characters")
                            String email,
                            @Min(value = 1, message = "Id can not be less than 1")
                            Integer id,
                            @Valid
                            DistrictRequest request);
}
