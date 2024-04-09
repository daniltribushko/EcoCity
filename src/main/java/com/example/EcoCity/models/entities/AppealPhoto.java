package com.example.EcoCity.models.entities;

import jakarta.persistence.*;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Сущность изображения обращения
 */
@Entity
@Table(name = "appeals_photos")
public class AppealPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url", nullable = false)
    private String photoUrl;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "appeal_id")
    private Appeal appeal;

    public AppealPhoto(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public AppealPhoto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Appeal getAppeal() {
        return appeal;
    }

    public void setAppeal(Appeal appeal) {
        this.appeal = appeal;
    }
}
