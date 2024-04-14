package com.example.EcoCity.services.db.imp;

import com.example.EcoCity.exceptions.appeals.AppealByIdNotFoundException;
import com.example.EcoCity.models.entities.Appeal;
import com.example.EcoCity.repositories.AppealRepository;
import com.example.EcoCity.services.db.DBServiceAppeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 12.04.2024
 * <p>
 * Реализация сервиса для работы с обращениями в бд
 */
@Service
public class DBServiceAppealImp implements DBServiceAppeal {
    private final AppealRepository appealRepository;

    @Autowired
    public DBServiceAppealImp(AppealRepository appealRepository) {
        this.appealRepository = appealRepository;
    }

    @Override
    public Appeal findById(Long id) {
        return appealRepository.findById(id)
                .orElseThrow(() -> new AppealByIdNotFoundException(id));
    }

    @Override
    public void save(Appeal object) {
        appealRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        Appeal appeal = findById(id);
        appealRepository.delete(appeal);
    }

}
