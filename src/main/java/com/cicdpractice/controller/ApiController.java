package com.cicdpractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Java CICD Practice Application! 🚀";
    }

    @GetMapping("/status")
    public String status() {
        return "{\"status\": \"Application is running successfully\", \"version\": \"1.0.0\"}";
    }

    @GetMapping("/info")
    public String info() {
        return "{\"application\": \"Java CICD Practice App\", " +
               "\"author\": \"DevOps Learner\", " +
               "\"environment\": \"Production\"}";
    }

    @PostMapping("/deploy")
    public String deploy() {
        return "{\"message\": \"Deployment successful!\", \"timestamp\": \"" + 
               System.currentTimeMillis() + "\"}";
    }
}
