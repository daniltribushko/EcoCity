package com.example.EcoCity.services.db;

import com.example.EcoCity.models.entities.EventType;

/**
 * @author Tribushko Danil
 * @since 30.04.2024
 * <p>
 * Сервис для работы с типами мероприятий в бд
 */
public interface DBServiceEventType extends CrudDatabaseService<EventType, Integer> {
    void update(EventType type);
}
