package com.example.EcoCity.services.db;

import com.example.EcoCity.models.entities.District;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Сервис для работы с районами в бд
 */
public interface DBServiceDistrict extends CrudDatabaseService<District, Integer> {
    /**
     * Обновление района
     *
     * @param district сущность района
     */
    void update(District district);
}
