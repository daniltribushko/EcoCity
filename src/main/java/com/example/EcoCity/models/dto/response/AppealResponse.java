package com.example.EcoCity.models.dto.response;

import com.example.EcoCity.models.entities.Appeal;
import com.example.EcoCity.models.entities.District;
import com.example.EcoCity.models.entities.MicroDistrict;
import com.example.EcoCity.models.enums.AppealStatus;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Dto ответа обращения
 */
public class AppealResponse {
    @Schema(description = "Appeal id",
            name = "id",
            type = "long",
            example = "1")
    private Long id;

    @Schema(description = "Appeal id",
            name = "id",
            type = "string",
            example = "New text")
    private String text;

    @Schema(description = "Appeal id",
            name = "id",
            type = "long",
            example = "1")
    private AppealStatus status;

    private AppealTypeResponse type;

    private DistrictResponse district;

    private MicroDistrictResponse microDistrict;

    private UserResponse author;

    public Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String text;
        private AppealStatus status;
        private AppealTypeResponse type;
        private DistrictResponse district;
        private MicroDistrictResponse microDistrict;
        private UserResponse author;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder status(AppealStatus status) {
            this.status = status;
            return this;
        }

        public Builder type(AppealTypeResponse type) {
            this.type = type;
            return this;
        }

        public Builder district(DistrictResponse district) {
            this.district = district;
            return this;
        }

        public Builder microDistrict(MicroDistrictResponse microDistrict) {
            this.microDistrict = microDistrict;
            return this;
        }

        public Builder author(UserResponse author) {
            this.author = author;
            return this;
        }

        public AppealResponse build() {
            AppealResponse appeal = new AppealResponse();
            appeal.id = this.id;
            appeal.text = this.text;
            appeal.status = this.status;
            appeal.type = this.type;
            appeal.district = this.district;
            appeal.microDistrict = this.microDistrict;
            appeal.author = this.author;
            return appeal;
        }
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public AppealStatus getStatus() {
        return status;
    }

    public AppealTypeResponse getType() {
        return type;
    }

    public DistrictResponse getDistrict() {
        return district;
    }

    public MicroDistrictResponse getMicroDistrict() {
        return microDistrict;
    }

    public UserResponse getAuthor() {
        return author;
    }

    public static AppealResponse mapFromEntity(Appeal appeal){
        District district = appeal.getDistrict();
        MicroDistrict microDistrict = appeal.getMicroDistrict();
        return new AppealResponse()
                .builder()
                .id(appeal.getId())
                .text(appeal.getText())
                .status(appeal.getStatus())
                .type(AppealTypeResponse.mapFromEntity(appeal.getType()))
                .district(new DistrictResponse(district.getId(), district.getName()))
                .microDistrict(new MicroDistrictResponse(microDistrict.getId(), microDistrict.getName()))
                .author(UserResponse.mapFromEntity(appeal.getAuthor()))
                .build();
    }
}
