package com.example.EcoCity.models.dto.response;

import com.example.EcoCity.models.entities.AppealRejectComment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 21.04.2024
 * <p>
 * Dto комментария к отклонению обращения
 */
public class AppealRejectCommentResponse {
    @Schema(description = "Appeal reject comment id",
            name = "id",
            type = "long",
            example = "1")
    private Long id;
    @Schema(description = "Appeal reject comment text",
            name = "comment",
            type = "string",
            example = "rejected")
    private String message;
    private LocalDateTime commentDate;

    public AppealRejectCommentResponse(Long id, String message, LocalDateTime commentDate) {
        this.id = id;
        this.message = message;
        this.commentDate = commentDate;
    }

    public AppealRejectCommentResponse(){}

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public static AppealRejectCommentResponse mapFromEntity(AppealRejectComment comment){
        return new AppealRejectCommentResponse(comment.getId(), comment.getMessage(), comment.getCommentDate());
    }
}
