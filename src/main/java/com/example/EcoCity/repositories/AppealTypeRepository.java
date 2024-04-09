package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.AppealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Репозиторий для работы с типами обращений
 */
@Repository
public interface AppealTypeRepository extends JpaRepository<AppealType, Integer> {
    boolean existsByName(String name);
}
