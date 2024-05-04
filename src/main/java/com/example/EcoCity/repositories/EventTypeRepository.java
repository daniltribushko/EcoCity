package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tribushko Danil
 * @since 30.04.2024
 * <p>
 * Репозиторий для работы с типами мероприятий
 */
@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Integer> {
    boolean existsByName(String name);
}
