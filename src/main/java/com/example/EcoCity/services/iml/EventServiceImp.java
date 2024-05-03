package com.example.EcoCity.services.iml;

import com.example.EcoCity.aspects.annotations.CheckUserEventAuthor;
import com.example.EcoCity.aspects.annotations.CheckUserOrganization;
import com.example.EcoCity.exceptions.events.EndDateBeforeStartDateException;
import com.example.EcoCity.exceptions.events.ParticipantsMoreMaxCountException;
import com.example.EcoCity.exceptions.events.StartDateAfterEndDateException;
import com.example.EcoCity.models.dto.request.CreateEventRequest;
import com.example.EcoCity.models.dto.request.EventRequest;
import com.example.EcoCity.models.dto.response.EventResponse;
import com.example.EcoCity.models.entities.District;
import com.example.EcoCity.models.entities.Event;
import com.example.EcoCity.models.entities.EventType;
import com.example.EcoCity.models.entities.MicroDistrict;
import com.example.EcoCity.models.enums.EventStatus;
import com.example.EcoCity.services.EventService;
import com.example.EcoCity.services.db.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 * <p>
 * Сервис для работы с мероприятиями
 */
@Service
public class EventServiceImp implements EventService {
    private final DBServiceEvent dbServiceEvent;
    private final DBServiceEventType dbServiceEventType;
    private final DBServiceDistrict dbServiceDistrict;
    private final DBServiceMicroDistrict dbServiceMicroDistrict;
    private final DBServiceUser dbServiceUser;

    @Autowired
    public EventServiceImp(DBServiceEvent dbServiceEvent,
                           DBServiceEventType dbServiceEventType,
                           DBServiceDistrict dbServiceDistrict,
                           DBServiceMicroDistrict dbServiceMicroDistrict,
                           DBServiceUser dbServiceUser) {
        this.dbServiceEvent = dbServiceEvent;
        this.dbServiceEventType = dbServiceEventType;
        this.dbServiceDistrict = dbServiceDistrict;
        this.dbServiceMicroDistrict = dbServiceMicroDistrict;
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    @CheckUserOrganization
    public EventResponse create(String email, CreateEventRequest request) {
        District district = dbServiceDistrict.findById(request.getDistrictId());
        MicroDistrict microDistrict = dbServiceMicroDistrict.findById(request.getMicroDistrictId());
        EventType eventType = dbServiceEventType.findById(request.getEventTypeId());

        LocalDateTime startDate = request.getStartDate();
        LocalDateTime endDate = request.getEndDate();

        if (startDate.isAfter(endDate)) {
            throw new StartDateAfterEndDateException();
        }
        if (endDate.isBefore(startDate)) {
            throw new EndDateBeforeStartDateException();
        }

        Event event = new Event()
                .builder()
                .name(request.getName())
                .status(EventStatus.WAITING_START)
                .startDate(startDate)
                .endDate(endDate)
                .description(request.getDescription())
                .maxCountParticipants(request.getMaxCountParticipants())
                .address(request.getAddress())
                .creator(dbServiceUser.findByEmail(email))
                .type(eventType)
                .district(district)
                .microDistrict(microDistrict)
                .build();

        dbServiceEvent.save(event);

        return EventResponse.mapFromEntity(event);
    }

    @Override
    @CheckUserEventAuthor
    public EventResponse update(String email, Long id, EventRequest request) {
        Event event = dbServiceEvent.findById(id);

        String name = request.getName();
        LocalDateTime startDate = request.getStartDate();
        LocalDateTime endDate = request.getEndDate();
        String description = request.getDescription();
        Integer maxCountParticipants = request.getMaxCountParticipants();
        String address = request.getAddress();

        if (name != null) {
            event.setName(name);
        }

        changeDate(event, startDate, endDate);

        if (description != null){
            event.setDescription(description);
        }

        if (maxCountParticipants != null){
            if (event.getParticipants().size() > maxCountParticipants){
                throw new ParticipantsMoreMaxCountException();
            } else {
                event.setMaxCountParticipant(maxCountParticipants);
            }
        }

        if (address != null){
            event.setAddress(address);
        }

        dbServiceEvent.update(event);

        return EventResponse.mapFromEntity(event);
    }

    private void changeDate(Event event, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate != null && endDate != null) {
            if (startDate.isAfter(endDate)) {
                throw new StartDateAfterEndDateException();
            } else {
                event.setStartDate(startDate);
                event.setEndDate(endDate);
            }
        } else if (startDate != null) {
            LocalDateTime oldEndDate = event.getEndDate();
            if (startDate.isAfter(oldEndDate)) {
                throw new StartDateAfterEndDateException();
            } else {
                event.setStartDate(startDate);
            }
        } else if(endDate != null) {
            LocalDateTime oldStartDate = event.getStartDate();
            if (endDate.isBefore(oldStartDate)) {
                throw new EndDateBeforeStartDateException();
            } else {
                event.setEndDate(endDate);
            }
        }
    }

    @Override
    public EventResponse findById(Long id) {
        return EventResponse.mapFromEntity(dbServiceEvent.findById(id));
    }

    @Override
    @CheckUserEventAuthor
    public void delete(String email, Long id) {
        dbServiceEvent.delete(id);
    }
}
