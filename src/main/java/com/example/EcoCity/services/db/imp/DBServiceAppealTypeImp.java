package com.example.EcoCity.services.db.imp;

import com.example.EcoCity.exceptions.appeals.AppealTypeByIdNotFoundException;
import com.example.EcoCity.models.entities.AppealType;
import com.example.EcoCity.repositories.AppealTypeRepository;
import com.example.EcoCity.services.db.DBServiceAppealType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Реализация сервиса по работе с типами обращений в бд
 */
@Service
public class DBServiceAppealTypeImp implements DBServiceAppealType {
    private final AppealTypeRepository appealTypeRepository;

    @Autowired
    public DBServiceAppealTypeImp(AppealTypeRepository appealTypeRepository){
        this.appealTypeRepository = appealTypeRepository;
    }

    @Override
    public AppealType findById(Integer id) {
        return appealTypeRepository.findById(id)
                .orElseThrow(() -> new AppealTypeByIdNotFoundException(id));
    }

    @Override
    public void save(AppealType object) {
        appealTypeRepository.save(object);
    }

    @Override
    public void delete(AppealType object) {
        appealTypeRepository.delete(object);
    }

    @Override
    public boolean existByName(String name) {
        return appealTypeRepository.existsByName(name);
    }
}
