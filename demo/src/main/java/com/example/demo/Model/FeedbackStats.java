package com.example.demo.Model;

import java.util.Map;

public class FeedbackStats {
    private long totalFeedback;
    private Map<String, Long> statusDistribution;
    private Map<Integer, Long> ratingDistribution;

    // Constructors
    public FeedbackStats() {}

    public FeedbackStats(long totalFeedback, Map<String, Long> statusDistribution, Map<Integer, Long> ratingDistribution) {
        this.totalFeedback = totalFeedback;
        this.statusDistribution = statusDistribution;
        this.ratingDistribution = ratingDistribution;
    }

    // Getters and Setters
    public long getTotalFeedback() {
        return totalFeedback;
    }

    public void setTotalFeedback(long totalFeedback) {
        this.totalFeedback = totalFeedback;
    }

    public Map<String, Long> getStatusDistribution() {
        return statusDistribution;
    }

    public void setStatusDistribution(Map<String, Long> statusDistribution) {
        this.statusDistribution = statusDistribution;
    }

    public Map<Integer, Long> getRatingDistribution() {
        return ratingDistribution;
    }

    public void setRatingDistribution(Map<Integer, Long> ratingDistribution) {
        this.ratingDistribution = ratingDistribution;
    }
}
