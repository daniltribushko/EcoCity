package com.example.EcoCity.models.dto.response;

import com.example.EcoCity.models.entities.Event;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.models.enums.EventStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 * <p>
 * Dto овтета мероприятия
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventResponse {
    private Long id;
    private String name;
    private EventStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private Integer maxCountParticipants;
    private String address;
    private UserResponse creator;
    private List<UserResponse> participants;
    private EventTypeResponse eventType;
    private DistrictResponse district;
    private MicroDistrictResponse microDistrict;

    public Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Long id;
        private String name;
        private EventStatus status;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String description;
        private Integer maxCountParticipants;
        private String address;
        private UserResponse creator;
        private List<UserResponse> participants;
        private EventTypeResponse eventType;
        private DistrictResponse district;
        private MicroDistrictResponse microDistrict;

        public Builder id(Long id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder status(EventStatus status){
            this.status = status;
            return this;
        }
        public Builder startDate(LocalDateTime startDate){
            this.startDate = startDate;
            return this;
        }
        public Builder endDate(LocalDateTime endDate){
            this.endDate = endDate;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder maxCountParticipants(Integer maxCountParticipants){
            this.maxCountParticipants = maxCountParticipants;
            return this;
        }
        public Builder address(String address){
            this.address = address;
            return this;
        }
        public Builder creator(UserResponse creator){
            this.creator = creator;
            return this;
        }
        public Builder participants(List<UserResponse> participants){
            this.participants = participants;
            return this;
        }
        public Builder eventType(EventTypeResponse eventType){
            this.eventType = eventType;
            return this;
        }
        public Builder district(DistrictResponse district){
            this.district = district;
            return this;
        }
        public Builder microDistrict(MicroDistrictResponse microDistrict){
            this.microDistrict = microDistrict;
            return this;
        }

        public EventResponse build(){
            EventResponse event = new EventResponse();

            event.id = this.id;
            event.name = this.name;
            event.status = this.status;
            event.startDate = this.startDate;
            event.endDate = this.endDate;
            event.description = this.description;
            event.maxCountParticipants = this.maxCountParticipants;
            event.address = this.address;
            event.creator = this.creator;
            event.participants = this.participants;
            event.eventType = this.eventType;
            event.district = this.district;
            event.microDistrict = this.microDistrict;

            return event;
        }
    }

    public EventResponse(Long id,
                         String name,
                         EventStatus status,
                         LocalDateTime startDate,
                         LocalDateTime endDate,
                         String description,
                         Integer maxCountParticipants,
                         String address,
                         UserResponse creator,
                         List<UserResponse> participants,
                         EventTypeResponse eventType,
                         DistrictResponse district,
                         MicroDistrictResponse microDistrict) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.maxCountParticipants = maxCountParticipants;
        this.address = address;
        this.creator = creator;
        this.participants = participants;
        this.eventType = eventType;
        this.district = district;
        this.microDistrict = microDistrict;
    }

    public EventResponse(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EventStatus getStatus() {
        return status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMaxCountParticipants() {
        return maxCountParticipants;
    }

    public String getAddress() {
        return address;
    }

    public UserResponse getCreator() {
        return creator;
    }

    public List<UserResponse> getParticipants() {
        return participants;
    }

    public EventTypeResponse getEventType() {
        return eventType;
    }

    public DistrictResponse getDistrict() {
        return district;
    }

    public MicroDistrictResponse getMicroDistrict() {
        return microDistrict;
    }

    public static EventResponse mapFromEntity(Event event){
        Set<User> participants = event.getParticipants();
        return new EventResponse()
                .builder()
                .id(event.getId())
                .name(event.getName())
                .status(event.getStatus())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .description(event.getDescription())
                .maxCountParticipants(event.getMaxCountParticipant())
                .creator(UserResponse.mapFromEntity(event.getCreator()))
                .participants(participants != null ? event.getParticipants()
                        .stream()
                        .map(UserResponse::mapFromEntity)
                        .toList() : null)
                .eventType(EventTypeResponse.mapFromEntity(event.getType()))
                .district(DistrictResponse.mapFromEntity(event.getDistrict()))
                .microDistrict(MicroDistrictResponse.mapFromEntity(event.getMicroDistrict()))
                .build();
    }
}
