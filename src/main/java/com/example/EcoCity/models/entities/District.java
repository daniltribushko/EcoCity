package com.example.EcoCity.models.entities;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Сущность района
 */
@Entity
@Table(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "district")
    private Set<MicroDistrict> microDistricts;

    public District(String name) {
        this.name = name;
    }

    public District() {
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

    public Set<MicroDistrict> getMicroDistricts() {
        return microDistricts;
    }

    public void setMicroDistricts(Set<MicroDistrict> microDistricts) {
        this.microDistricts = microDistricts;
    }
}
