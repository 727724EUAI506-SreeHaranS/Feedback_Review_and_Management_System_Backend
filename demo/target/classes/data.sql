INSERT INTO feedback (user_id, product_id, rating, comment, status, created_at) VALUES
('user1', 'PROD001', 5, 'Excellent product! Very satisfied with the quality.', 'APPROVED', NOW()),
('user2', 'PROD002', 4, 'Good product, but delivery was slow.', 'PENDING', NOW()),
('user3', 'PROD001', 3, 'Average quality, could be better.', 'PENDING', NOW()),
('user4', 'PROD003', 2, 'Not satisfied with the product quality.', 'REJECTED', NOW()),
('user5', 'PROD002', 5, 'Amazing product! Highly recommended.', 'APPROVED', NOW()),
('user6', 'PROD001', 4, 'Good value for money.', 'APPROVED', NOW()),
('user7', 'PROD003', 1, 'Very poor quality, requesting refund.', 'REJECTED', NOW()),
('user8', 'PROD002', 5, 'Perfect! Exactly what I needed.', 'APPROVED', NOW());