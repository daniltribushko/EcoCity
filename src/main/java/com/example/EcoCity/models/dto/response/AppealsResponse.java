package com.example.EcoCity.models.dto.response;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Dto ответа обращений
 */
public class AppealsResponse {
    private List<AppealResponse> appeals;

    public AppealsResponse(List<AppealResponse> appeals) {
        this.appeals = appeals;
    }

    public AppealsResponse() {
    }

    public List<AppealResponse> getAppeals() {
        return appeals;
    }
}
