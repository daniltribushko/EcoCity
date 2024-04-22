package com.example.EcoCity.models.dto.response;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 22.04.2024
 *
 * Dto ответа нескольких пользователей
 */
public class UsersResponse {
    private List<UserResponse> users;

    public UsersResponse(List<UserResponse> users) {
        this.users = users;
    }

    public UsersResponse(){}

    public List<UserResponse> getUsers() {
        return users;
    }
}
