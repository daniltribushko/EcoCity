package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 * <p>
 * Репозиторий для работы с сущностями пользователей
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
