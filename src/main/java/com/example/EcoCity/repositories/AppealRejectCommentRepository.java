package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.AppealRejectComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tribushko Danil
 * @since 21.04.2024
 * <p>
 * Репозиторий для работы с комментариями для отклонения обращения
 */
@Repository
public interface AppealRejectCommentRepository extends JpaRepository<AppealRejectComment, Long> {
}
