package com.example.EcoCity.models.entities;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Тип обращения
 */
@Entity
@Table(name = "appeals_types")
public class AppealType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "type")
    private Set<Appeal> appeals;

    public AppealType(String name) {
        this.name = name;
    }

    public AppealType() {
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

    public Set<Appeal> getAppeals() {
        return appeals;
    }

    public void setAppeals(Set<Appeal> appeals) {
        this.appeals = appeals;
    }
}
