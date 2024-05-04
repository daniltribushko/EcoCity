package com.example.EcoCity.models.entities;

import com.example.EcoCity.models.enums.EventStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 30.04.2024
 * <p>
 * Сущность мероприятия
 */
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EventStatus status;
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;
    @Column(name = "description", nullable = false, length = 500)
    private String description;
    @Column(name = "max_count_participant", nullable = false)
    private Integer maxCountParticipant;
    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "creator_id")
    private User creator;
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "events_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> participants;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "type_id")
    private EventType type;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "district_id")
    private District district;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "micro_district_id")
    private MicroDistrict microDistrict;

    public Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private EventStatus status;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String description;
        private Integer maxCountParticipants;
        private String address;
        private User creator;
        private Set<User> participants;
        private EventType type;
        private District district;
        private MicroDistrict microDistrict;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder status(EventStatus status){
            this.status = status;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate){
            this.endDate = endDate;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder maxCountParticipants(Integer maxCountParticipants) {
            this.maxCountParticipants = maxCountParticipants;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder creator(User creator) {
            this.creator = creator;
            return this;
        }

        public Builder participants(Set<User> participants) {
            this.participants = participants;
            return this;
        }
        public Builder type(EventType type){
            this.type = type;
            return this;
        }
        public Builder district(District district){
            this.district = district;
            return this;
        }
        public Builder microDistrict(MicroDistrict microDistrict){
            this.microDistrict = microDistrict;
            return this;
        }

        public Event build() {
            Event event = new Event();

            event.id = this.id;
            event.name = this.name;
            event.status = this.status;
            event.startDate = this.startDate;
            event.endDate = this.endDate;
            event.description = this.description;
            event.maxCountParticipant = this.maxCountParticipants;
            event.address = this.address;
            event.creator = this.creator;
            event.participants = this.participants;
            event.type = this.type;
            event.district = this.district;
            event.microDistrict = this.microDistrict;

            return event;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxCountParticipant() {
        return maxCountParticipant;
    }

    public void setMaxCountParticipant(Integer maxCountParticipant) {
        this.maxCountParticipant = maxCountParticipant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public MicroDistrict getMicroDistrict() {
        return microDistrict;
    }

    public void setMicroDistrict(MicroDistrict microDistrict) {
        this.microDistrict = microDistrict;
    }
}
