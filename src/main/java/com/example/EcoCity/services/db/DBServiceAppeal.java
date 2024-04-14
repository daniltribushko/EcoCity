package com.example.EcoCity.services.db;

import com.example.EcoCity.models.entities.Appeal;
import com.example.EcoCity.models.entities.AppealPhoto;

/**
 * @author Tribushko Danil
 * @since 12.04.2024
 * <p>
 * Сервис для работы с обращениями в бд
 */
public interface DBServiceAppeal extends CrudDatabaseService<Appeal, Long> {
    void deleteAppealPhoto(AppealPhoto appealPhoto);

}
