package com.example.EcoCity.models.entities;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 03.05.2024
 * <p>
 * Сущность типа мероприятия
 */
@Entity
@Table(name = "events_type")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "type")
    private Set<Event> events;

    public EventType(String name) {
        this.name = name;
    }

    public EventType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
