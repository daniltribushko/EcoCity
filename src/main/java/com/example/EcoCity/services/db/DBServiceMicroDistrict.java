package com.example.EcoCity.services.db;

import com.example.EcoCity.models.entities.MicroDistrict;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Сервис для работы с микрорайонами в бд
 */
public interface DBServiceMicroDistrict extends CrudDatabaseService<MicroDistrict, Integer> {
    /**
     * Обновление микрорайона в бд
     *
     * @param microDistrict сущность микрорайона
     */
    void update(MicroDistrict microDistrict);
}
