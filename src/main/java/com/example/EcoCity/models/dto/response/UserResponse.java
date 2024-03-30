package com.example.EcoCity.models.dto.response;

import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.models.enums.RecordState;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 * <p>
 * Dto ответа пользователя
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserResponse {
    @Schema(description = "User id",
            name = "id",
            type = "long",
            example = "1")
    private Long id;

    @Schema(description = "User email",
            name = "email",
            type = "string",
            example = "test.email@bk.ru",
            minLength = 7)
    private String email;

    @Schema(description = "User surname",
            name = "surname",
            type = "string",
            example = "Ivanov")
    private String surname;

    @Schema(description = "User name",
            name = "user",
            type = "string",
            example = "Ivan")
    private String name;

    @Schema(description = "User record state",
            name = "record state",
            type = "recordState",
            example = "ACTIVE")
    private RecordState recordState;

    @Schema(description = "User creation date",
            name = "creation date",
            type = "localDateTine")
    private LocalDateTime createDate;

    @Schema(description = "Date the user was last online",
            name = "last online date",
            type = "localDateTine")
    private LocalDateTime lastOnlineDate;

    public static class Builder {
        private Long id;
        private String email;
        private String surname;
        private String name;
        private RecordState recordState;
        private LocalDateTime createDate;
        private LocalDateTime lastOnlineDate;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder recordState(RecordState recordState) {
            this.recordState = recordState;
            return this;
        }

        public Builder createDate(LocalDateTime createDate){
            this.createDate = createDate;
            return this;
        }

        public Builder lastOnlineDate(LocalDateTime lastOnlineDate){
            this.lastOnlineDate = lastOnlineDate;
            return this;
        }

        public UserResponse build(){
            UserResponse response = new UserResponse();
            response.id = this.id;
            response.email = this.email;
            response.surname = this.surname;
            response.name = this.name;
            response.recordState = this.recordState;
            response.createDate = this.createDate;
            response.lastOnlineDate = this.lastOnlineDate;
            return response;
        }

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public RecordState getRecordState() {
        return recordState;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getLastOnlineDate() {
        return lastOnlineDate;
    }

    public Builder builder(){
        return new Builder();
    }

    public static UserResponse mapFromEntity(User user) {
        return new UserResponse()
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .surname(user.getSurname())
                .name(user.getName())
                .recordState(user.getRecordState())
                .createDate(user.getCreateDate())
                .lastOnlineDate(user.getLastOnlineDate())
                .build();
    }
}
