package com.example.demo.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Feedback;
import com.example.demo.Model.FeedbackStats;
import com.example.demo.Model.FeedbackStatus;
import com.example.demo.Repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback submitFeedback(Feedback feedback) {
        feedback.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        feedback.setStatus(FeedbackStatus.PENDING);
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getUserFeedback(String userId) {
        return feedbackRepository.findByUserId(userId);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback updateFeedbackStatus(Long id, String status) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow();
        feedback.setStatus(FeedbackStatus.valueOf(status));
        return feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public Page<Feedback> getFilteredFeedback(String status, Integer rating, String productId, String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        FeedbackStatus feedbackStatus = null;
        if (status != null && !status.isEmpty()) {
            try {
                feedbackStatus = FeedbackStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                // Invalid status, will be treated as null
            }
        }
        return feedbackRepository.findFilteredFeedback(feedbackStatus, rating, productId, search, pageable);
    }

    @Override
    public List<String> getUniqueProductIds() {
        return feedbackRepository.findDistinctProductIds();
    }

    @Override
    public FeedbackStats getFeedbackStats() {
        List<Feedback> allFeedback = feedbackRepository.findAll();
        long totalFeedback = allFeedback.size();

        Map<String, Long> statusDistribution = allFeedback.stream()
            .collect(Collectors.groupingBy(f -> f.getStatus().toString(), Collectors.counting()));

        Map<Integer, Long> ratingDistribution = allFeedback.stream()
            .collect(Collectors.groupingBy(Feedback::getRating, Collectors.counting()));

        return new FeedbackStats(totalFeedback, statusDistribution, ratingDistribution);
    }
}
