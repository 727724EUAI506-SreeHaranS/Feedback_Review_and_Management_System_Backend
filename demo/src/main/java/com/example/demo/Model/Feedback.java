package com.example.demo.Model;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(indexes = {
    @Index(name = "idx_feedback_status", columnList = "status"),
    @Index(name = "idx_feedback_rating", columnList = "rating"),
    @Index(name = "idx_feedback_product_id", columnList = "productId")
})
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String productId;
    private int rating;
    private String comment;

    @Enumerated(EnumType.STRING)
    private FeedbackStatus status = FeedbackStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();


    // Getters and Setters
    // ...
    public FeedbackStatus getStatus() {
        return status;
    }
    public void setStatus(FeedbackStatus status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
}