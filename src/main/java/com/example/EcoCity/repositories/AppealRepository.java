package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.Appeal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Репозиторий для работы с обращениями
 */
public interface AppealRepository extends JpaRepository<Appeal, Long> {

}
