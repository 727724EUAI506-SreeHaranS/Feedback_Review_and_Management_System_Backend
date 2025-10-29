package com.example.demo.Service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.example.demo.Model.User;

public interface UserService {
    User register(User user);
    User authenticate(String username, String password);
    Page<User> getAllUsers(int page, int size);
    Map<String, Object> getUserStats();
    User updateUserRole(Long id, String role);
    void deleteUser(Long id);
    Map<String, Object> getDashboardData();
}
