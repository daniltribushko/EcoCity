package com.example.EcoCity.services.db;

import com.example.EcoCity.models.entities.Event;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 * <p>
 * Сервис для работы с мероприятиями в бд
 */
public interface DBServiceEvent extends CrudDatabaseService<Event, Long> {
    void update(Event event);
    List<Event> findAll();
}
