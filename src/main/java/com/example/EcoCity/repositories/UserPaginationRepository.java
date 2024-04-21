package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tribushko Danil
 * @since 21.04.2024
 * <p>
 * Репозиторий для работы с пользователями с пагинацией
 */
@Repository
public interface UserPaginationRepository extends PagingAndSortingRepository<User, Long> {
}
