package com.example.EcoCity.models.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tribushko Danil
 * @since 12.04.2024
 * <p>
 * Dto запроса фото обращения
 */
public class AppealPhotoRequest {

    @NotNull(message = "File can not be null")
    private MultipartFile file;

    public AppealPhotoRequest(MultipartFile file) {
        this.file = file;
    }

    public AppealPhotoRequest() {
    }

    public MultipartFile getFile() {
        return file;
    }
}
