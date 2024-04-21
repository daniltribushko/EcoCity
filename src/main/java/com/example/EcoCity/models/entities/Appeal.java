package com.example.EcoCity.models.entities;

import com.example.EcoCity.models.enums.AppealStatus;
import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Сущность обращения
 */
@Entity
@Table(name = "appeals")
public class Appeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text", nullable = false, length = 1000)
    private String text;
    @Column(name = "address", nullable = false)
    private String address;
    @Enumerated(EnumType.STRING)
    private AppealStatus status;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "appeal")
    private Set<AppealPhoto> photos;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "appeal_type_id")
    private AppealType type;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "district_id")
    private District district;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "micro_district_id")
    private MicroDistrict microDistrict;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "author_id")
    private User author;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "appeal")
    private AppealRejectComment comment;
    public Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String text;
        private String address;
        private AppealStatus status;
        private Set<AppealPhoto> photos;
        private AppealType type;
        private District district;
        private MicroDistrict microDistrict;
        private User author;
        private AppealRejectComment comment;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder text(String text){
            this.text = text;
            return this;
        }
        public Builder address(String address){
            this.address = address;
            return this;
        }
        public Builder status(AppealStatus status){
            this.status = status;
            return this;
        }

        public Builder photos(Set<AppealPhoto> photos){
            this.photos = photos;
            return this;
        }

        public Builder type(AppealType type){
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

        public Builder author(User author){
            this.author = author;
            return this;
        }

        public Builder message(AppealRejectComment comment){
            this.comment = comment;
            return this;
        }

        public Appeal build(){
            Appeal appeal = new Appeal();
            appeal.id = this.id;
            appeal.text = this.text;
            appeal.status = this.status;
            appeal.photos = this.photos;
            appeal.type = this.type;
            appeal.district = this.district;
            appeal.microDistrict = this.microDistrict;
            appeal.author = this.author;
            appeal.address = this.address;
            appeal.comment = this.comment;

            return appeal;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AppealStatus getStatus() {
        return status;
    }

    public void setStatus(AppealStatus status) {
        this.status = status;
    }

    public Set<AppealPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<AppealPhoto> photos) {
        this.photos = photos;
    }

    public AppealType getType() {
        return type;
    }

    public void setType(AppealType type) {
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AppealRejectComment getComment() {
        return comment;
    }

    public void setComment(AppealRejectComment comment) {
        this.comment = comment;
    }
}
