package com.example.EcoCity.services;

import com.example.EcoCity.models.dto.request.EventTypeRequest;
import com.example.EcoCity.models.dto.response.EventTypeResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Tribushko Danil
 * @since 30.04.2024
 * <p>
 * Сервис по работе с типами мероприятий
 */
public interface EventTypeService {
    EventTypeResponse create(@Email
                             @Size(min = 7, message = "Email must be contain 7 characters")
                             String email,
                             @Valid
                             EventTypeRequest request);

    EventTypeResponse update(@Email
                             @Size(min = 7, message = "Email must be contain 7 characters")
                             String email,
                             @Min(value = 1, message = "Id can not be less than 1")
                             Integer id,
                             @Valid
                             EventTypeRequest request);

    void delete(@Email
                @Size(min = 7, message = "Email must be contain 7 characters")
                String email,
                @Min(value = 1, message = "Id can not be less than 1")
                Integer id);

    EventTypeResponse findById(@Min(value = 1, message = "Id can not be less than 1")
                               Integer id);
}
