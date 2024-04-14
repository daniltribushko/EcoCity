package com.example.EcoCity.services.db;

import com.example.EcoCity.models.entities.AppealType;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Сервис для работы с обращениями в бд
 */
public interface DBServiceAppealType extends CrudDatabaseService<AppealType, Integer> {
    /**
     * Обновление типа обращения
     *
     * @param appealType сущность типа обращений
     */
    void update(AppealType appealType);
}
