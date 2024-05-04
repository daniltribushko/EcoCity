package com.example.EcoCity.services.iml;

import com.example.EcoCity.aspects.annotations.CheckUserAdmin;
import com.example.EcoCity.models.dto.request.EventTypeRequest;
import com.example.EcoCity.models.dto.response.EventTypeResponse;
import com.example.EcoCity.models.entities.EventType;
import com.example.EcoCity.services.EventTypeService;
import com.example.EcoCity.services.db.DBServiceEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushki Danil
 * @since 30.04.2024
 * <p>
 * Реализация сервиса по работе с типами мероприятий
 */
@Service
public class EventTypeServiceImp implements EventTypeService {
    private final DBServiceEventType dbServiceEventType;

    @Autowired
    public EventTypeServiceImp(DBServiceEventType dbServiceEventType){
        this.dbServiceEventType = dbServiceEventType;
    }

    @Override
    @CheckUserAdmin
    public EventTypeResponse create(String email,
                                    EventTypeRequest request) {
        EventType type = new EventType(request.getName());
        dbServiceEventType.save(type);
        return EventTypeResponse.mapFromEntity(type);
    }

    @Override
    @CheckUserAdmin
    public EventTypeResponse update(String email,
                                    Integer id, EventTypeRequest request) {
        EventType type = dbServiceEventType.findById(id);
        type.setName(request.getName());
        dbServiceEventType.update(type);
        return EventTypeResponse.mapFromEntity(type);
    }

    @Override
    @CheckUserAdmin
    public void delete(String email,
                       Integer id) {
        dbServiceEventType.delete(id);
    }

    @Override
    public EventTypeResponse findById(Integer id) {
        return EventTypeResponse.mapFromEntity(dbServiceEventType.findById(id));
    }
}
