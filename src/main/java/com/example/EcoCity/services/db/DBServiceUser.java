package com.example.EcoCity.services.db;

import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.services.CrudDatabaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 *
 * Сервис для выполнения crud операций над пользователями в бд
 */
public interface DBServiceUser extends CrudDatabaseService<User, Long>, UserDetailsService {
    User findByEmail(String email);
    boolean existByEmail(String email);
}
