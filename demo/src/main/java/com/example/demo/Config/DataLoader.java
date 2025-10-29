package com.example.demo.Config;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.Model.Feedback;
import com.example.demo.Model.FeedbackStatus;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.FeedbackRepository;
import com.example.demo.Repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create admin user if not exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
        
        if (feedbackRepository.count() == 0) {
            Feedback[] feedbacks = {
                createFeedback("user1", "PROD001", 5, "Excellent product! Very satisfied.", FeedbackStatus.APPROVED),
                createFeedback("user2", "PROD002", 4, "Good product, delivery was slow.", FeedbackStatus.PENDING),
                createFeedback("user3", "PROD001", 3, "Average quality, could be better.", FeedbackStatus.PENDING),
                createFeedback("user4", "PROD003", 2, "Not satisfied with quality.", FeedbackStatus.REJECTED),
                createFeedback("user5", "PROD002", 5, "Amazing! Highly recommended.", FeedbackStatus.APPROVED)
            };
            feedbackRepository.saveAll(Arrays.asList(feedbacks));
        }
    }

    private Feedback createFeedback(String userId, String productId, int rating, String comment, FeedbackStatus status) {
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setProductId(productId);
        feedback.setRating(rating);
        feedback.setComment(comment);
        feedback.setStatus(status);
        feedback.setCreatedAt(LocalDateTime.now());
        return feedback;
    }
}