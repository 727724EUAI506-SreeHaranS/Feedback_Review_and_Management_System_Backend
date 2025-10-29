package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.Model.Feedback;
import com.example.demo.Model.FeedbackStats;

public interface FeedbackService {
    Feedback submitFeedback(Feedback feedback);
    List<Feedback> getUserFeedback(String userId);
    List<Feedback> getAllFeedback();
    Page<Feedback> getFilteredFeedback(String status, Integer rating, String productId, String search, int page, int size);
    List<String> getUniqueProductIds();
    FeedbackStats getFeedbackStats();
    Feedback updateFeedbackStatus(Long id, String status);
    void deleteFeedback(Long id);
}
