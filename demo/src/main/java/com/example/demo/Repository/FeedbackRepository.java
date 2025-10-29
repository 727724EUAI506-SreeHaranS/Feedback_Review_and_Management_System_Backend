package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Model.Feedback;
import com.example.demo.Model.FeedbackStatus;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUserId(String userId);

    @Query("SELECT DISTINCT f.productId FROM Feedback f WHERE f.productId IS NOT NULL")
    List<String> findDistinctProductIds();

    @Query("SELECT f FROM Feedback f WHERE " +
           "(:status IS NULL OR f.status = :status) AND " +
           "(:rating IS NULL OR f.rating = :rating) AND " +
           "(:productId IS NULL OR f.productId = :productId) AND " +
           "(:search IS NULL OR LOWER(f.comment) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Feedback> findFilteredFeedback(@Param("status") FeedbackStatus status,
                                        @Param("rating") Integer rating,
                                        @Param("productId") String productId,
                                        @Param("search") String search,
                                        Pageable pageable);
}
