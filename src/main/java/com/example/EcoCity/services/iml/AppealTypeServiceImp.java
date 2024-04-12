package com.example.EcoCity.services.iml;

import com.example.EcoCity.aspects.annotations.CheckUserAdmin;
import com.example.EcoCity.exceptions.appeals.AppealTypeAlreadyExistException;
import com.example.EcoCity.models.dto.request.AppealTypeRequest;
import com.example.EcoCity.models.dto.response.AppealResponse;
import com.example.EcoCity.models.dto.response.AppealTypeResponse;
import com.example.EcoCity.models.dto.response.AppealsResponse;
import com.example.EcoCity.models.entities.Appeal;
import com.example.EcoCity.models.entities.AppealType;
import com.example.EcoCity.services.AppealTypeService;
import com.example.EcoCity.services.db.DBServiceAppealType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Реализация сервиса по работе с типами обращений
 */
@Service
public class AppealTypeServiceImp implements AppealTypeService {
    private final DBServiceAppealType dbServiceAppealType;

    @Autowired
    public AppealTypeServiceImp(DBServiceAppealType dbServiceAppealType){
        this.dbServiceAppealType = dbServiceAppealType;
    }
    @Override
    @CheckUserAdmin
    public AppealTypeResponse create(String email, AppealTypeRequest request) {
        String name = request.getName();
        AppealType type = new AppealType(name);
        dbServiceAppealType.save(type);
        return new AppealTypeResponse(type.getId(), type.getName());
    }

    @Override
    @CheckUserAdmin
    public AppealTypeResponse update(String email, Integer id, AppealTypeRequest request) {
        AppealType type = dbServiceAppealType.findById(id);
        type.setName(request.getName());
        dbServiceAppealType.update(type);
        return AppealTypeResponse.mapFromEntity(type);
    }

    @Override
    @CheckUserAdmin
    public void delete(String email, Integer id) {
        dbServiceAppealType.delete(id);
    }

    @Override
    public AppealTypeResponse findById(Integer id) {
        AppealType type = dbServiceAppealType.findById(id);
        return new AppealTypeResponse(type.getId(), type.getName());
    }

    @Override
    public AppealsResponse getAppeals(Integer id) {
        Set<Appeal> appeals = dbServiceAppealType.findById(id).getAppeals();
        return new AppealsResponse(appeals
                .stream()
                .map(AppealResponse::mapFromEntity)
                .toList());
    }
}
