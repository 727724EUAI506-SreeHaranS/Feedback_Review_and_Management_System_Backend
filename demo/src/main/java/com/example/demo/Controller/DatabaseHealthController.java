package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class DatabaseHealthController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/database")
    public ResponseEntity<Map<String, Object>> checkDatabaseConnection() {
        Map<String, Object> response = new HashMap<>();
        
        try (Connection connection = dataSource.getConnection()) {
            response.put("status", "UP");
            response.put("database", connection.getMetaData().getDatabaseProductName());
            response.put("version", connection.getMetaData().getDatabaseProductVersion());
            response.put("url", connection.getMetaData().getURL());
            response.put("message", "Database connection successful");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "DOWN");
            response.put("error", e.getMessage());
            response.put("message", "Database connection failed");
            return ResponseEntity.status(500).body(response);
        }
    }
}