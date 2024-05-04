package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 * <p>
 * Репозиторий для работы с мероприятиями
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByName(String name);
}
