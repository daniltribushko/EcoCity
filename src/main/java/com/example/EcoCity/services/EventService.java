package com.example.EcoCity.services;

import com.example.EcoCity.models.dto.request.CreateEventRequest;
import com.example.EcoCity.models.dto.request.EventRequest;
import com.example.EcoCity.models.dto.response.EventResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * @author Tribushko Danil
 * @since 03.05.2024
 * <p>
 * Сервис для работы с мероприятиями
 */
public interface EventService {
    EventResponse create(@Email
                         @Size(min = 7, message = "Email must be contain 7 characters")
                         String email,
                         @Valid
                         CreateEventRequest request);

    EventResponse update(@Email
                         @Size(min = 7, message = "Email must be contain 7 characters")
                         String email,
                         @Min(value = 1, message = "Id can not be less than 1")
                         Long id,
                         @Valid
                         EventRequest request);

    EventResponse findById(@Min(value = 1, message = "Id can not be less than 1")
                           Long id);

    void delete(@Email
                @Size(min = 7, message = "Email must be contain 7 characters")
                String email,
                @Min(value = 1, message = "Id can not be less than 1")
                Long id);
}
