package com.example.EcoCity.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "micro_districts")
public class MicroDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "district_id")
    private District district;

    public MicroDistrict(Integer id, String name, District district) {
        this.id = id;
        this.name = name;
        this.district = district;
    }

    public MicroDistrict(){}

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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
