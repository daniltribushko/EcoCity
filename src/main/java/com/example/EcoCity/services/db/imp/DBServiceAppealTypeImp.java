package com.example.EcoCity.services.db.imp;

import com.example.EcoCity.exceptions.appeals.AppealTypeAlreadyExistException;
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
        String name = object.getName();
        if (appealTypeRepository.existsByName(name)){
            throw new AppealTypeAlreadyExistException(name);
        }
        appealTypeRepository.save(object);
    }

    @Override
    public void delete(Integer id) {
        AppealType appealType = findById(id);
        appealTypeRepository.delete(appealType);
    }

    @Override
    public void update(AppealType appealType) {
        appealTypeRepository.save(appealType);
    }
}
