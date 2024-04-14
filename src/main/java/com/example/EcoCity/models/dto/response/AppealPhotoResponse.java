package com.example.EcoCity.models.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Tribushko Danil
 * @since 14.01.2024
 * <p>
 * Dto ответа на получение изобажений обращения
 */
public class AppealPhotoResponse {
    @Schema(description = "Appeal photo file name",
            name = "filename",
            type = "string",
            example = "test_file.txt")
    private String filename;

    public AppealPhotoResponse(String filename) {
        this.filename = filename;
    }

    public AppealPhotoResponse() {
    }

    public String getFilename() {
        return filename;
    }
}
