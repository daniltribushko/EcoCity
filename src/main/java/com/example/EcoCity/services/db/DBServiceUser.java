package com.example.EcoCity.services.db;

import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.models.enums.RecordState;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 *
 * Сервис для выполнения crud операций над пользователями в бд
 */
public interface DBServiceUser extends CrudDatabaseService<User, Long>, UserDetailsService {
    User findByEmail(String email);
    void update(User user);
    List<User> findAll();
    List<User> findAllWithPagination(Integer page,
                                     Integer perPage,
                                     RecordState recordState,
                                     LocalDateTime createDate,
                                     LocalDateTime lastDateOnOnline,
                                     String role);
}
