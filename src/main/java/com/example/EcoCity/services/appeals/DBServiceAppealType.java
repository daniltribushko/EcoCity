package com.example.EcoCity.services.appeals;

import com.example.EcoCity.models.entities.AppealType;
import com.example.EcoCity.services.CrudDatabaseService;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Сервис для работы с обращениями в бд
 */
public interface DBServiceAppealType extends CrudDatabaseService<AppealType, Integer> {
    boolean existByName(String name);
}
