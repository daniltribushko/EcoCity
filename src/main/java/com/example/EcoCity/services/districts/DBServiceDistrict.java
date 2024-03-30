package com.example.EcoCity.services.districts;

import com.example.EcoCity.models.entities.District;
import com.example.EcoCity.services.CrudDatabaseService;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Сервис для работы с районами в бд
 */
public interface DBServiceDistrict extends CrudDatabaseService<District, Integer> {
    /**
     * Проверка существует ли района с указанным названием в бд
     *
     * @param name название района
     * @return находится ли район с указанным названием в бд
     */
    boolean existByName(String name);
}
