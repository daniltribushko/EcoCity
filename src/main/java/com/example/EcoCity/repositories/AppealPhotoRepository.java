package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.AppealPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Репозиторий для работы с изображениями обращений
 */
@Repository
public interface AppealPhotoRepository extends JpaRepository<AppealPhoto, Long> {
}
