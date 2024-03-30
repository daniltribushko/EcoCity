package com.example.EcoCity.services;

import com.example.EcoCity.models.entities.Role;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 *
 * Сервис для работы с ролями
 */
public interface RoleService {
    /**
     * Поиск роли по названию
     * @param name название роли
     * @return сущность роли
     */
    Role findByName(
            @NotBlank(message = "Name can not be blank")
            String name);
}
