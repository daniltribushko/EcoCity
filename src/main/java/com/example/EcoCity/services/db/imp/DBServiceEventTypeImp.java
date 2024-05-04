package com.example.EcoCity.services.db.imp;

import com.example.EcoCity.exceptions.events.EventTypeAlreadyExistException;
import com.example.EcoCity.exceptions.events.EventTypeByIdNotFoundException;
import com.example.EcoCity.models.entities.EventType;
import com.example.EcoCity.repositories.EventTypeRepository;
import com.example.EcoCity.services.db.DBServiceEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 30.04.2024
 * <p>
 * Реализация сервиса по работе с типами мероприятий в бд
 */
@Service
public class DBServiceEventTypeImp implements DBServiceEventType {
    private final EventTypeRepository eventTypeRepository;

    @Autowired
    public DBServiceEventTypeImp(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public EventType findById(Integer id) {
        return eventTypeRepository.findById(id)
                .orElseThrow(() -> new EventTypeByIdNotFoundException(id));
    }

    @Override
    public void save(EventType object) {
        String name = object.getName();
        if (eventTypeRepository.existsByName(name)) {
            throw new EventTypeAlreadyExistException(name);
        }
        eventTypeRepository.save(object);
    }

    @Override
    public void delete(Integer id) {
        EventType eventType = findById(id);
        eventTypeRepository.delete(eventType);
    }

    @Override
    public void update(EventType type) {
        eventTypeRepository.save(type);
    }
}
