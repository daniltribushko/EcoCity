package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Репозиторий по работе с районами
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    boolean existsByName(String name);
}
