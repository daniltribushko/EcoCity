package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.MicroDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Репозиторий для работы с микрорайонами
 */
@Repository
public interface MicroDistrictRepository extends JpaRepository<MicroDistrict, Integer> {
    boolean existsByName(String name);
}
