package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 * <p>
 * Репозиторий для работы с ролями
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
