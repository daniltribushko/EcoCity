package com.example.EcoCity.services;

import com.example.EcoCity.models.dto.request.AppealTypeRequest;
import com.example.EcoCity.models.dto.response.AppealResponse;
import com.example.EcoCity.models.dto.response.AppealTypeResponse;
import com.example.EcoCity.models.dto.response.AppealsResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Сервис для работы с типом обращения
 */
public interface AppealTypeService {
    AppealTypeResponse create(@Email
                              @Size(min = 7, message = "Email must be contain 7 characters")
                              String email,
                              @Valid
                              AppealTypeRequest request);

    AppealTypeResponse update(@Email
                              @Size(min = 7, message = "Email must be contain 7 characters")
                              String email,
                              @Min(value = 1, message = "Id can not be less than 1")
                              Integer id,
                              @Valid
                              AppealTypeRequest request);

    void delete(@Email
                @Size(min = 7, message = "Email must be contain 7 characters")
                String email,
                @Min(value = 1, message = "Id can not be less than 1")
                Integer id);

    AppealTypeResponse findById(@Min(value = 1, message = "Id can not be less than 1")
                                Integer id);

    AppealsResponse getAppeals(@Min(value = 1, message = "Id can not be less than 1")
                               Integer id);
}
