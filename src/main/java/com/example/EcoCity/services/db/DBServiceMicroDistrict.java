package com.example.EcoCity.services.db;

import com.example.EcoCity.models.entities.MicroDistrict;
import com.example.EcoCity.services.CrudDatabaseService;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Сервис для работы с микрорайонами в бд
 */
public interface DBServiceMicroDistrict extends CrudDatabaseService<MicroDistrict, Integer> {
    /**
     * Проверка существует ли микрорайон в бд с указанным названием
     *
     * @param name название микрорайона
     * @return находится ли микрорайон с указанным названием в бд
     */
    boolean existByName(String name);
}
