package com.cicdpractice.service;

import org.springframework.stereotype.Service;

@Service
public class AppService {

    public String getApplicationInfo() {
        return "Java CICD Practice Application v1.0.0";
    }

    public boolean validateInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        return input.length() >= 3;
    }

    public String processData(String data) {
        if (!validateInput(data)) {
            return "Invalid input";
        }
        return "Processed: " + data.toUpperCase();
    }

    public String getHealthCheck() {
        return "Application is healthy and running!";
    }
}
