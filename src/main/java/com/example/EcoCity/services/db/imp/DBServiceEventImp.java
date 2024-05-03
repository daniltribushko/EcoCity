package com.example.EcoCity.services.db.imp;

import com.example.EcoCity.exceptions.events.EventAlreadyExistException;
import com.example.EcoCity.exceptions.events.EventByIdNotFoundException;
import com.example.EcoCity.models.entities.Event;
import com.example.EcoCity.repositories.EventRepository;
import com.example.EcoCity.services.db.DBServiceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 * <p>
 * Реализация сервиса по работе с мероприятиями в бд
 */
@Service
public class DBServiceEventImp implements DBServiceEvent {
    private final EventRepository eventRepository;

    @Autowired
    public DBServiceEventImp(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventByIdNotFoundException(id));
    }

    @Override
    public void save(Event object) {
        String name = object.getName();
        if (eventRepository.existsByName(name)){
            throw new EventAlreadyExistException(name);
        }
        eventRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        Event event = findById(id);
        eventRepository.delete(event);
    }

    @Override
    public void update(Event event) {
        eventRepository.save(event);
    }
}
