package com.example.EcoCity.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 21.04.2024
 *
 * Сущность коммен
 */
@Entity
@Table(name = "appeal_commis")
public class AppealRejectComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "commit_date", nullable = false)
    private LocalDateTime commentDate;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "appeal_id")
    private Appeal appeal;

    public AppealRejectComment(String message, LocalDateTime commentDate, Appeal appeal) {
        this.message = message;
        this.commentDate = commentDate;
        this.appeal = appeal;
    }

    public AppealRejectComment(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public Appeal getAppeal() {
        return appeal;
    }

    public void setAppeal(Appeal appeal) {
        this.appeal = appeal;
    }
}
